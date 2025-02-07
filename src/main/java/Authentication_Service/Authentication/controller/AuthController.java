package Authentication_Service.Authentication.controller;
// package Authentication_Service.Authentication;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.service.AuthService;
import Authentication_Service.Authentication.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // @PostMapping("/register")
    // public ResponseEntity<?> registerUser(@RequestBody User user) {
    //     return ResponseEntity.ok(authService.registerUser(user));
    // }
    @PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    try {
        return ResponseEntity.ok(authService.registerUser(user));
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = authService.login(user.getUsername(), user.getPassword());
        if (token != null) {
            return ResponseEntity.ok("Bearer: " + token); // Return the JWT if successful
        }
        return ResponseEntity.status(401).body("Invalid credentials"); // Return 401 if authentication fails
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7); // Remove "Bearer "
            String username = jwtUtil.extractUsername(token);

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Map<String, String> userInfo = Map.of(
                    "username", user.getUsername(),
                    "email", user.getEmail()
            );

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }
    
}
