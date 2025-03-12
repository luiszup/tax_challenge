package br.com.zup.tax_challenge.infra.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "d367ec6ce788f25f51e3749cdd3925cfae17a67d28ccafcfd8f08eb9b3c21be4";
    private long jwtExpirationDate = 3600000;

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationDate))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        return token;
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token);
        return true;
    }
}
