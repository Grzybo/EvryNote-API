package grzybo.evrynote.api;

import grzybo.evrynote.model.UserModel;
import grzybo.evrynote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public UserController(UserService userService, InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.userService = userService;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @GetMapping
    public List<UserModel> getAll(){return userService.getAll();}

    @PostMapping
    public void add(@NonNull @RequestBody UserModel user){
        userService.add(user);
        inMemoryUserDetailsManager.createUser(User.withUsername(user.getUsername()).password("{noop}"+ user.getPassword()).roles("USER").build());
    }

    @GetMapping(path = "{id}")
    public Optional<UserModel> getById(@PathVariable("id") Long id){ return userService.getByID(id);}

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id){
        userService.deleteByID(id);}

    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id") Long id, @NonNull @RequestBody UserModel user ){
        userService.updateByID(id, user);
        inMemoryUserDetailsManager.createUser(User.withUsername(user.getUsername()).password("{noop}"+user.getPassword()).roles("USER").build());
    }

}
