package security;


import io.jsonwebtoken.*; 
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	
	  @Value("${jwt.secret}")
	    private String jwtSecret;

	    private long jwtExpirationMs = 86400000; // 1 day

	    public String generateToken(String email) {
	        return Jwts.builder()
	                .setSubject(email)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public String extractUsername(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	            return true;
	        } catch (JwtException | IllegalArgumentException e) {
	            return false;
	        }
	    }

	    private Key getSignKey() {
	        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	    }

}
