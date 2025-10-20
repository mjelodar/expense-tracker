package com.snapp.expense_tracker.user.util;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    String generateToken(String username, Long userId, long expiresIn) {
        Date expiration = new Date(new Date().getTime() + (expiresIn * 1000));
        return Jwts.builder().
                claim("userId", userId).
                claim("username", username).
                claim("expireAt", expiration).
                expiration(expiration.)
    }
}
