package com.snapp.expense_tracker.auth.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${app.secret}")
    private String SECRET_KEY;

    public String generateToken(String username, Long userId, long expiresIn) {
        Date expiration = new Date(new Date().getTime() + expiresIn);
        return Jwts.builder().
                claim("userId", userId).
                claim("username", username).
                claim("expireAt", expiration).
                expiration(expiration).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))).
                compact();
    }
}
