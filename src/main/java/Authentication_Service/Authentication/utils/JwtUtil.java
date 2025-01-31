// package Authentication_Service.Authentication.utils;

// import io.jsonwebtoken.*;
// import org.springframework.stereotype.Component;
// import java.util.Date;

// @Component
// public class JwtUtil {
//     private final String secret = "your_secret_key";
//     private final long expiration = 3600000;

//     public String generateToken(String username) {
//         try {
//         return Jwts.builder()
//             .setSubject(username)
//             .setIssuedAt(new Date())
//             .setExpiration(new Date(System.currentTimeMillis() + expiration))
//             .signWith(SignatureAlgorithm.HS256, secret)
//             .compact();

//         } catch (Exception e) {
//             System.err.println("Error generating token: " + e.getMessage());
//         return null;
//         }

//     }

//     public String extractUsername(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser()
//             .setSigningKey(secret)
//             .parseClaimsJws(token);
//             return true;
//         } catch (JwtException e) {
//             System.err.println("Token validation error: " + e.getMessage());
//         }
//         return false;
//     }
// }

// package Authentication_Service.Authentication.utils;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import Authentication_Service.Authentication.config.JwtConfig;
// import io.jsonwebtoken.*;
// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtUtil {
//     // private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a secure key
//     // private final long expiration = 3600000; // 1 hour
//     private final JwtConfig jwtConfig;

//     public JwtUtil(JwtConfig jwtConfig) {
//         this.jwtConfig = jwtConfig;
//     }

//     public String generateToken(String username) {
//         try {
//             return Jwts.builder()
//                     .setSubject(username)
//                     .setIssuedAt(new Date())
//                     .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration))
//                     .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret()) // Use the Key object here
//                     .compact();
//         } catch (Exception e) {
//             System.err.println("Error generating token: " + e.getMessage());
//             return null;
//         }
//     }

//     public String extractUsername(String token) {
//         try {
//             Claims claims = Jwts.parserBuilder()
//                     .setSigningKey(jwtConfig.getSecret()) // Use the Key object here
//                     .build()
//                     .parseClaimsJws(token)
//                     .getBody();
//             return claims.getSubject();
//         } catch (Exception e) {
//             System.err.println("Error extracting username: " + e.getMessage());
//             return null;
//         }
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                 .setSigningKey(jwtConfig.getSecret()) // Use the Key object here
//                 .build()
//                 .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             System.err.println("Token validation error: " + e.getMessage());
//         }
//         return false;
//     }
// }

// package Authentication_Service.Authentication.utils;

// import Authentication_Service.Authentication.config.JwtConfig;
// import io.jsonwebtoken.*;
// import org.springframework.stereotype.Component;
// import java.util.Date;
// import io.jsonwebtoken.security.Keys;
// import java.security.Key;


// @Component
// public class JwtUtil {

//     private final JwtConfig jwtConfig;

//     public JwtUtil(JwtConfig jwtConfig) {
//         this.jwtConfig = jwtConfig;
//     }

//     public String generateToken(String username) {
//         try {
//             return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
//                 .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
//                 .compact();
//         } catch (Exception e) {
//             System.err.println("Error generating token: " + e.getMessage());
//             return null;
//         }
//     }

//     public String extractUsername(String token) {
//         return Jwts.parser()
//                 .setSigningKey(jwtConfig.getSecret())
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser()
//                 .setSigningKey(jwtConfig.getSecret())
//                 .parseClaimsJws(token);
//             return true;
//         } catch (JwtException e) {
//             System.err.println("Token validation error: " + e.getMessage());
//         }
//         return false;
//     }
// }

package Authentication_Service.Authentication.utils;

import Authentication_Service.Authentication.config.JwtConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    private final Key key;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes()); // Create a Key object from the secret
        System.out.println("JwtUtil initialized with secret: " + jwtConfig.getSecret());
    }

    public String generateToken(String username) {
        try {
            return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(key, SignatureAlgorithm.HS256) // Use Key for signing
                .compact();
        } catch (Exception e) {
            System.err.println("Error generating token: " + e.getMessage());
            return null;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder() // Use parserBuilder instead of parser
                .setSigningKey(key) // Use Key for validation
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key) // Use Key for validation
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.err.println("Token validation error: " + e.getMessage());
            return false;
        }
    }
}
