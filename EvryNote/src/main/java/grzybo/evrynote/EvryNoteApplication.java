package grzybo.evrynote;

import grzybo.evrynote.model.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class EvryNoteApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(EvryNoteApplication.class, args);
        System.out.println("HELLO EVRYNOTE!");
    }
}
