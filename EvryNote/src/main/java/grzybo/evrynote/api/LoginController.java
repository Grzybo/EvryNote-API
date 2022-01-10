package grzybo.evrynote.api;

import grzybo.evrynote.config.LoginCredentials;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody LoginCredentials loginCredentials){

        return "Login Successful";
    }

    @GetMapping ("/secured")
    public String secured(){
        return "Login Successful";
    }

}
