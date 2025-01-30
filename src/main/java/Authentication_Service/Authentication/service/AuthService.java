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
import Authentication_Service.Authentication.security.JwtUtil;
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

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return null;
    }
}
