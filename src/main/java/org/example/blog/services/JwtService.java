package org.example.blog.services;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
public class JwtService {

    public String generateToken (UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", "https://localhost:8080/user/");
        Instant now = Instant.now();
        return Jwts.builder()
                .claims (claims)
                .subject (userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration (Date.from(now.plusSeconds (10*60)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode("8VETUoDBjrbK0vtTGOhV9XLEUPSOS25qk3UaiQgOXfc0KuiM");
        return Keys.hmacShaKeyFor(decodedKey);
    }

}
