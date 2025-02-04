// // // package Authentication_Service.Authentication.service;

// // // import Authentication_Service.Authentication.entity.User;
// // // import Authentication_Service.Authentication.repository.UserRepository;
// // // import Authentication_Service.Authentication.security.JwtUtil;
// // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // import org.springframework.stereotype.Service;

// // // @Service
// // // public class AuthService {
// // //     private final UserRepository userRepository;
// // //     private final BCryptPasswordEncoder passwordEncoder;
// // //     private final JwtUtil jwtUtil;

// // //     public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
// // //         this.userRepository = userRepository;
// // //         this.passwordEncoder = passwordEncoder;
// // //         this.jwtUtil = jwtUtil;
// // //     }

// // //     public User registerUser(User user) {
// // //         user.setPassword(passwordEncoder.encode(user.getPassword()));
// // //         return userRepository.save(user);
// // //     }

// // //     public String login(String username, String password) {
// // //         User user = userRepository.findByUsername(username).orElse(null);
// // //         if (user != null && passwordEncoder.matches(password, user.getPassword())) {
// // //             return jwtUtil.generateToken(username);
// // //         }
// // //         return null;
// // //     }
// // // }

// // package Authentication_Service.Authentication.service;

// // import java.util.Optional;

// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.stereotype.Service;

// // import Authentication_Service.Authentication.entity.User;
// // import Authentication_Service.Authentication.repository.UserRepository;
// // import Authentication_Service.Authentication.utils.JwtUtil;

// // @Service
// // public class AuthService {
// //     private final UserRepository userRepository;
// //     private final PasswordEncoder passwordEncoder; // Use PasswordEncoder interface
// //     private final JwtUtil jwtUtil;

// //     public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
// //         this.userRepository = userRepository;
// //         this.passwordEncoder = passwordEncoder;
// //         this.jwtUtil = jwtUtil;
// //     }

// //     public String registerUser(User user) {
// //         // Check if the username already exists
// //         Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
// //         if (existingUser.isPresent()) {
// //             return "Username already exists!"; // Reject registration if username exists
// //         }

// //         // Encrypt password
// //         user.setPassword(passwordEncoder.encode(user.getPassword()));

// //         // Save new user to the repository
// //         userRepository.save(user);

// //         return "User registered successfully!";
// //     }

   
// //     public String login(String username, String password) {
// //         User user = userRepository.findByUsername(username).orElse(null);
// //         if (user == null) {
// //             System.out.println("User not found: " + username);
// //             return null;
// //         }
// //         if (!passwordEncoder.matches(password, user.getPassword())) {
// //             System.out.println("Invalid password for user: " + username);
// //             return null;
// //         }
// //         System.out.println("Login successful for user: " + username);
// //         return jwtUtil.generateToken(username);
// //     }
    
    
// // }

// package Authentication_Service.Authentication.service;

// import Authentication_Service.Authentication.entity.Role;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.RoleRepository;
// import Authentication_Service.Authentication.repository.UserRepository;
// import Authentication_Service.Authentication.utils.JwtUtil;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import java.util.Optional;
// import java.util.Set;

// @Service
// public class AuthService {
//     private final UserRepository userRepository;
//     private final RoleRepository roleRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.roleRepository = roleRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.jwtUtil = jwtUtil;
//     }

//     public String registerUser(User user) {
//         Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
//         if (existingUser.isPresent()) {
//             return "Username already exists!";
//         }

//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         Role userRole = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("USER role not found"));
//         user.setRoles(Set.of(userRole));

//         userRepository.save(user);
//         return "User registered successfully!";
//     }

//     // public String login(String username, String password) {
//     //     User user = userRepository.findByUsername(username).orElse(null);
//     //     if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//     //         return null;
//     //     }
//     //     return jwtUtil.generateToken(username);
//     // }

//     public String login(String username, String password) {
//         User user = userRepository.findByUsername(username).orElse(null);
//         if (user == null) {
//             System.out.println("User not found: " + username);
//             return null;
//         }
//         if (!passwordEncoder.matches(password, user.getPassword())) {
//             System.out.println("Invalid password for user: " + username);
//             return null;
//         }
//         System.out.println("Login successful for user: " + username);
//         return jwtUtil.generateToken(username, user.getRoles()); // Pass roles to generateToken
//     }
    
// }


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
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));
        user.setRoles(Set.of(userRole));
    
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
