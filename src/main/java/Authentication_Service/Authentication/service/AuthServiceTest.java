// package Authentication_Service.Authentication.service;

// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.UserRepository;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.when;

// public class AuthServiceTest {

//     private final UserRepository userRepository = Mockito.mock(UserRepository.class);
//     private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
//     private final JwtUtil jwtUtil = Mockito.mock(JwtUtil.class);
//     private final AuthService authService = new AuthService(userRepository, null, passwordEncoder, jwtUtil);

//     @Test
//     void testLoginSuccessful() {
//         User user = new User();
//         user.setUsername("testuser");
//         user.setPassword("encodedpassword");

//         when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
//         when(passwordEncoder.matches("rawpassword", "encodedpassword")).thenReturn(true);
//         when(jwtUtil.generateToken(Mockito.anyString(), Mockito.anySet())).thenReturn("mockToken");

//         String token = authService.login("testuser", "rawpassword");

//         assertNotNull(token, "Token should not be null");
//     }
// }
