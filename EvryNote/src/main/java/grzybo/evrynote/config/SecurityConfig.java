package grzybo.evrynote.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Configuration
@EnableWebSecurity
@EnableCaching
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationFailureHandler failureHandler;
    private final RestAuthenticationSuccessHandler successHandler;
    private final String secret;
    private final UserRepository userRepository;

    public SecurityConfig(RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          RestAuthenticationFailureHandler authenticationFailureHandler,
                          @Value("${jwt.secret}") String secret,
                          DataSource dataSource, ObjectMapper objectMapper,
                          UserRepository userRepository) {
        this.successHandler = authenticationSuccessHandler;
        this.failureHandler = authenticationFailureHandler;
        this.secret = secret;
        this.objectMapper = objectMapper;
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    /**
     *@Override
     *     protected void configure(AuthenticationManagerBuilder builder) throws Exception {
     *         builder.inMemoryAuthentication()
     *                 .withUser("test")
     *                 .password("{noop}test")
     *                 .roles("USER");
     *     }
     */

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        List<UserModel> userList = userRepository.findAll();
        for (UserModel u : userList){
            if(u.isEnabled()){
                users.put(u.getUsername(), "{noop}"+u.getPassword()+",ROLE_USER,enabled"); //TODO do poprawy
                System.out.println(u.getUsername());
            }
        }
        users.put("test","{noop}test,ROLE_USER,enabled");
        users.put("user","{noop}test,ROLE_USER,enabled");
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("**/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), super.userDetailsService(), secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        authenticationFilter.setAuthenticationManager(super.authenticationManager());
        return authenticationFilter;
    }

}
