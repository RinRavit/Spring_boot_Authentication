
// // // // package Authentication_Service.Authentication.security;

// // // // import org.springframework.context.annotation.Bean;
// // // // import org.springframework.context.annotation.Configuration;
// // // // import org.springframework.security.authentication.AuthenticationManager;
// // // // import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// // // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // // import org.springframework.security.web.SecurityFilterChain;
// // // // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// // // // // import jakarta.servlet.http.HttpServletRequest;

// // // // @Configuration
// // // // public class SecurityConfig {

// // // //     private final JwtAuthFilter jwtAuthFilter;

// // // //     public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
// // // //         this.jwtAuthFilter = jwtAuthFilter;
// // // //     }

// // // //     @Bean
// // // //     public PasswordEncoder passwordEncoder() {
// // // //         return new BCryptPasswordEncoder();
// // // //     }

// // // //     @Bean
// // // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // // //         http
// // // //             .csrf(csrf -> csrf.disable()) // Disable CSRF using the new syntax
// // // //             .authorizeHttpRequests(auth -> auth
// // // //                 .requestMatchers("/auth/register", "/auth/login").permitAll() // Public endpoints
// // // //                 .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN role can access /admin
// // // //                 .anyRequest().authenticated() // Protect all other endpoints
// // // //             )
// // // //             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

// // // //         return http.build();
// // // //     }

// // // //     @Bean
// // // //     public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
// // // //         return configuration.getAuthenticationManager();
// // // //     }
// // // // }


// // // package Authentication_Service.Authentication.security;

// // // import org.springframework.context.annotation.Bean;
// // // import org.springframework.context.annotation.Configuration;
// // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // import org.springframework.security.web.SecurityFilterChain;

// // // @Configuration
// // // public class SecurityConfig {

// // //     @Bean
// // //     public PasswordEncoder passwordEncoder() {
// // //         return new BCryptPasswordEncoder();
// // //     }

// // //     @Bean
// // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // //         http
// // //             .csrf(csrf -> csrf.disable())
// // //             .authorizeHttpRequests(auth -> auth
// // //                 .requestMatchers("/auth/register", "/auth/login").permitAll()
// // //                 .requestMatchers("/superadmin/**").hasRole("SUPER_ADMIN")
// // //                 .requestMatchers("/admin/**").hasRole("ADMIN")
// // //                 .requestMatchers("/user/**").hasRole("USER")
// // //                 .anyRequest().authenticated()
// // //             );
// // //         return http.build();
// // //     }
// // // }


// // package Authentication_Service.Authentication.security;

// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// // import org.springframework.security.web.authentication.logout.*;

// // @Configuration
// // public class SecurityConfig {

// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }

// //    @Bean
// // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //     http
// //         .csrf(csrf -> csrf.disable())
// //         .authorizeHttpRequests(auth -> auth
// //             .requestMatchers("/auth/register", "/auth/login", "/oauth2/**").permitAll()
// //             .requestMatchers("/superadmin/**").hasAuthority("MANAGE_USERS")
// //             .requestMatchers("/admin/**").hasAnyAuthority("READ_USERS", "WRITE_USERS")
// //             .requestMatchers("/user/**").hasAuthority("READ_USERS")
// //             .anyRequest().authenticated()
// //         )
// //         .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
// //     return http.build();
// // }

// // }

// package Authentication_Service.Authentication.security;

// import Authentication_Service.Authentication.utils.JwtUtil;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtAuthFilter jwtAuthFilter;

//     public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
//         this.jwtAuthFilter = jwtAuthFilter;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/register", "/auth/login", "/oauth2/**").permitAll()
//                 .requestMatchers("/superadmin/**").hasAuthority("SUPER_ADMIN")
//                 .requestMatchers("/admin/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
//                 .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN", "SUPER_ADMIN")
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }

package Authentication_Service.Authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers("/superadmin/**").hasRole("SUPER_ADMIN")
                        .requestMatchers("/courses/create", "/courses/assign").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/courses/my-courses").hasRole("USER")
                        .requestMatchers("/courses/my-created-courses").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
