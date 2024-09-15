package com.example.ecommerce.util;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.function.Function;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;


    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date from token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract claims from token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    SecretKey toSecretKey(String key) {
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
    private Claims extractAllClaims(String token) {

        // rebuild key using SecretKeySpec

        return Jwts.parser().verifyWith(toSecretKey(jwtSecret)).build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(toSecretKey(jwtSecret))
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

}
