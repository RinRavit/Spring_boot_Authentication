
// // // package Authentication_Service.Authentication.utils;

// // // import java.security.Key;
// // // import java.util.Date;

// // // import org.springframework.stereotype.Component;

// // // import Authentication_Service.Authentication.config.JwtConfig;
// // // import io.jsonwebtoken.JwtException;
// // // import io.jsonwebtoken.Jwts;
// // // import io.jsonwebtoken.SignatureAlgorithm;
// // // import io.jsonwebtoken.security.Keys;

// // // @Component
// // // public class JwtUtil {

// // //     private final JwtConfig jwtConfig;
// // //     private final Key key;

// // //     public JwtUtil(JwtConfig jwtConfig) {
// // //         this.jwtConfig = jwtConfig;
// // //         this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes()); // Create a Key object from the secret
// // //         System.out.println("JwtUtil initialized with secret: " + jwtConfig.getSecret());
// // //     }

// // //     public String generateToken(String username) {
// // //         try {
// // //             return Jwts.builder()
// // //                 .setSubject(username)
// // //                 .setIssuedAt(new Date())
// // //                 .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
// // //                 .signWith(key, SignatureAlgorithm.HS256) // Use Key for signing
// // //                 .compact();
// // //         } catch (Exception e) {
// // //             System.err.println("Error generating token: " + e.getMessage());
// // //             return null;
// // //         }
// // //     }

// // //     public String extractUsername(String token) {
// // //         return Jwts.parserBuilder() // Use parserBuilder instead of parser
// // //                 .setSigningKey(key) // Use Key for validation
// // //                 .build()
// // //                 .parseClaimsJws(token)
// // //                 .getBody()
// // //                 .getSubject();
// // //     }

// // //     public boolean validateToken(String token) {
// // //         try {
// // //             Jwts.parserBuilder()
// // //                 .setSigningKey(key) // Use Key for validation
// // //                 .build()
// // //                 .parseClaimsJws(token);
// // //             return true;
// // //         } catch (JwtException e) {
// // //             System.err.println("Token validation error: " + e.getMessage());
// // //             return false;
// // //         }
// // //     }
// // // }


// // package Authentication_Service.Authentication.utils;

// // import Authentication_Service.Authentication.config.JwtConfig;
// // import Authentication_Service.Authentication.entity.Role;
// // import io.jsonwebtoken.*;
// // import io.jsonwebtoken.security.Keys;
// // import org.springframework.stereotype.Component;

// // import java.security.Key;
// // import java.util.Date;
// // import java.util.HashSet;
// // import java.util.List;
// // import java.util.Set;
// // import java.util.stream.Collectors;

// // @Component
// // public class JwtUtil {

// //     private final JwtConfig jwtConfig;
// //     private final Key key;

// //     public JwtUtil(JwtConfig jwtConfig) {
// //         this.jwtConfig = jwtConfig;
// //         this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
// //         System.out.println("JwtUtil initialized with secret: " + jwtConfig.getSecret());
// //     }

// //     public String generateToken(String username, Set<Role> roles) {
// //         try {
// //             // Convert roles to a list of role names
// //             Set<String> roleNames = roles.stream()
// //                     .map(Role::getName)
// //                     .collect(Collectors.toSet());

// //             return Jwts.builder()
// //                     .setSubject(username)
// //                     .claim("roles", roleNames) // Add roles as a claim
// //                     .setIssuedAt(new Date())
// //                     .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
// //                     .signWith(key, SignatureAlgorithm.HS256)
// //                     .compact();
// //         } catch (Exception e) {
// //             System.err.println("Error generating token: " + e.getMessage());
// //             return null;
// //         }
// //     }

// //     public String extractUsername(String token) {
// //         return Jwts.parserBuilder()
// //                 .setSigningKey(key)
// //                 .build()
// //                 .parseClaimsJws(token)
// //                 .getBody()
// //                 .getSubject();
// //     }

// //     public Set<String> extractRoles(String token) {
// //         Claims claims = Jwts.parserBuilder()
// //                 .setSigningKey(key)
// //                 .build()
// //                 .parseClaimsJws(token)
// //                 .getBody();
    
// //         // Retrieve the roles claim and cast it to List<String>
// //         Object rolesObject = claims.get("roles");
// //         if (rolesObject instanceof List) {
// //             @SuppressWarnings("unchecked")
// //             List<String> rolesList = (List<String>) rolesObject;
// //             return new HashSet<>(rolesList); // Convert List to Set
// //         } else {
// //             throw new IllegalArgumentException("Roles claim is not a List");
// //         }
// //     }
    


// //     public boolean validateToken(String token) {
// //         try {
// //             Jwts.parserBuilder()
// //                     .setSigningKey(key)
// //                     .build()
// //                     .parseClaimsJws(token);
// //             return true;
// //         } catch (JwtException e) {
// //             System.err.println("Token validation error: " + e.getMessage());
// //             return false;
// //         }
// //     }
// // }


// package Authentication_Service.Authentication.utils;

// import Authentication_Service.Authentication.config.JwtConfig;
// import Authentication_Service.Authentication.entity.Role;
// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

// @Component
// public class JwtUtil {

//     private final JwtConfig jwtConfig;
//     private final Key key;

//     public JwtUtil(JwtConfig jwtConfig) {
//         this.jwtConfig = jwtConfig;
//         this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
//     }

//     public String generateToken(String username, Set<Role> roles) {
//         Set<String> roleNames = roles.stream()
//                 .map(Role::getName)
//                 .collect(Collectors.toSet());

//         return Jwts.builder()
//                 .setSubject(username)
//                 .claim("roles", roleNames)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }

//     public Set<String> extractRoles(String token) {
//         Claims claims = Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();

//         Object rolesObject = claims.get("roles");
//         if (rolesObject instanceof List) {
//             @SuppressWarnings("unchecked")
//             List<String> rolesList = (List<String>) rolesObject;
//             return new HashSet<>(rolesList);
//         } else {
//             throw new IllegalArgumentException("Roles claim is not a List");
//         }
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                     .setSigningKey(key)
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (JwtException e) {
//             return false;
//         }
//     }
// }


package Authentication_Service.Authentication.utils;

import Authentication_Service.Authentication.config.JwtConfig;
import Authentication_Service.Authentication.entity.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    private final Key key;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
        System.out.println("JwtUtil initialized with secret: " + jwtConfig.getSecret());
    }

    public String generateToken(String username, Set<Role> roles) {
        try {
            Set<String> roleNames = roles.stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());

            return Jwts.builder()
                    .setSubject(username)
                    .claim("roles", roleNames) // Include roles in token
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            System.err.println("Error generating token: " + e.getMessage());
            return null;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Set<String> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Object rolesObject = claims.get("roles");
        if (rolesObject instanceof List) {
            @SuppressWarnings("unchecked")
            List<String> rolesList = (List<String>) rolesObject;
            return new HashSet<>(rolesList);
        }
        throw new IllegalArgumentException("Roles claim is not a valid list.");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.err.println("Token validation error: " + e.getMessage());
            return false;
        }
    }
}
