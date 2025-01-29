package authentication.auth;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    @GetMapping("/login")
    public String login() {
        return "Login successful!";
    }
    
}
