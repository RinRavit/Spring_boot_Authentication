// // package Authentication_Service.Authentication.security;

// // import Authentication_Service.Authentication.service.UserService;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// // @Configuration
// // public class SecurityConfig {

// //     private final JwtAuthFilter jwtAuthFilter; // Replace JsonFilter with JwtAuthFilter
// //     private final UserService userService;

// //     public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService) {
// //         this.jwtAuthFilter = jwtAuthFilter;
// //         this.userService = userService;
// //     }

// //     @Bean
// // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //     http.csrf(csrf -> csrf.disable()) // Disable CSRF
// //         .authorizeHttpRequests(auth -> auth
// //             .requestMatchers("/auth/**").permitAll()  // Public endpoints
// //             .requestMatchers("/admin/**").hasRole("ADMIN") // Admin protected endpoints
// //             .anyRequest().authenticated() // All other endpoints require authentication
// //         )
// //         .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add the JWT filter

// //     return http.build();
// // }
    

// //     @Bean
// //     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
// //         return authenticationConfiguration.getAuthenticationManager();
// //     }

// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }
// // }
// package Authentication_Service.Authentication.security;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

package Authentication_Service.Authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register", "/auth/login").permitAll() // Allow unauthenticated access
                .anyRequest().authenticated() // Protect all other endpoints
            );

        return http.build();
    }
}
