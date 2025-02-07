package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.RoleRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // public String registerUser(User user) {
    //     Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
    //     if (existingUser.isPresent()) {
    //         return "Username already exists!";
    //     }

    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
    //     user.setRoles(Set.of(userRole));

    //     userRepository.save(user);
    //     return "User registered successfully!";
    // }
    // public String registerUser(User user) {
    //     user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
    //     Role userRole = roleRepository.findByName("USER")
    //             .orElseThrow(() -> new RuntimeException("USER role not found"));
    //     user.setRoles(Set.of(userRole));
    
    //     userRepository.save(user);
    //     return "User registered successfully!";
    // }
    public String registerUser(User user) {
        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already taken");
        }
    
        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        // Assign the default role (USER)
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));
        user.setRoles(Set.of(userRole));
    
        // Save the new user
        userRepository.save(user);
        return "User registered successfully!";
    }
    
    

    public String login(String username, String password) {
        logger.info("Attempting login for username: {}", username);
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            logger.warn("User not found: {}", username);
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.warn("Invalid password for user: {}", username);
            return null;
        }

        logger.info("Login successful for user: {}", username);
        return jwtUtil.generateToken(username, user.getRoles());
    }
}
