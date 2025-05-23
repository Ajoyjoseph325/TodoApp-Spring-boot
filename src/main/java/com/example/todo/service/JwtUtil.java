package com.example.todo.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    // private String secret = "mySecretKey";
    private long expirationMs = 86400000; // 1 day



private String secret = "4qQpxkDmZa0yJ2JH3P+uwzY6gGZJZVjyk6E3hZG2zNOxlT1quQkk9XK2oKPhT7Ys8FX6epwRPmZK25r+bA8vXA==";

private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
}
    

    public String generateToken(String username,Integer id) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
