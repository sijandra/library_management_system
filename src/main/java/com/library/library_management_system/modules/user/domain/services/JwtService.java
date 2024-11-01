package com.library.library_management_system.modules.user.domain.services;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
    private final String SECRET_KEY = "secretkeylms";
    private final RedisService redisService;

    @Autowired
    public JwtService(RedisService redisService) {
        this.redisService = redisService;
    }

    public String generateToken(String userId) {
        long expirationTime = 1000 * 60 * 60;

        String token = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        this.redisService.setValue(token, userId, expirationTime, TimeUnit.MILLISECONDS);

        return token;
    }

    public boolean validateToken(String token, String userId) {
        if (this.isTokenInvalidated(token)) {
            return false;
        }

        final String extractedUserId = this.extractMetadata(token);
        String storedUserId = this.redisService.getValue(token);
        return (extractedUserId.equals(userId) && storedUserId != null && storedUserId.equals(userId)
                && !this.isTokenExpired(token));
    }

    public String extractMetadata(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.extractAllClaims(token).getExpiration().before(new Date());
    }

    public void invalidateToken(String token) {
        this.redisService.setValue(token, "invalid", 60 * 60, TimeUnit.SECONDS);
    }

    private boolean isTokenInvalidated(String token) {
        return !this.redisService.hasKey(token);
    }
}
