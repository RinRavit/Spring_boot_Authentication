
package Authentication_Service.Authentication.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import Authentication_Service.Authentication.config.JwtConfig;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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
