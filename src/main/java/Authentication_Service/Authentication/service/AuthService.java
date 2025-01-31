// package Authentication_Service.Authentication.service;

// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.UserRepository;
// import Authentication_Service.Authentication.security.JwtUtil;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class AuthService {
//     private final UserRepository userRepository;
//     private final BCryptPasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.jwtUtil = jwtUtil;
//     }

//     public User registerUser(User user) {
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return userRepository.save(user);
//     }

//     public String login(String username, String password) {
//         User user = userRepository.findByUsername(username).orElse(null);
//         if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//             return jwtUtil.generateToken(username);
//         }
//         return null;
//     }
// }

package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.utils.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Use PasswordEncoder interface
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // public String login(String username, String password) {
    //     User user = userRepository.findByUsername(username).orElse(null);
    //     if (user != null && passwordEncoder.matches(password, user.getPassword())) {
    //         String token =jwtUtil.generateToken(username);
    //         System.out.println("Generated Token: " + token); // Debug log
    //         return token;
    //     }
    //     return null;
    // }
    // public String login(String username, String password) {
    //     User user = userRepository.findByUsername(username).orElse(null);
    //     if (user != null && passwordEncoder.matches(password, user.getPassword())) {
    //         return jwtUtil.generateToken(username); // Return token if credentials are valid
    //     }
    //     return null; // Return null if credentials are invalid
    // }
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("User not found: " + username);
            return null;
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Invalid password for user: " + username);
            return null;
        }
        System.out.println("Login successful for user: " + username);
        return jwtUtil.generateToken(username);
    }
    
    
}
