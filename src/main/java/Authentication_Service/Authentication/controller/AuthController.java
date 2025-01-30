package Authentication_Service.Authentication.controller;
// package Authentication_Service.Authentication;

import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = authService.login(user.getUsername(), user.getPassword());
        return token != null ? ResponseEntity.ok("Bearer " + token) : ResponseEntity.status(401).body("Invalid credentials");
    }
}
