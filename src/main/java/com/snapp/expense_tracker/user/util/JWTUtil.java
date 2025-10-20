package com.snapp.expense_tracker.user.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET_KEY =
            "F867KS86JD867HF86KF04985926D96C90030DD58429D2751AC1BDB645BY654C0123456789ABCDEF0123456789ABCDEF";
    public static String generateToken(String username, Long userId, long expiresIn) {
        Date expiration = new Date(new Date().getTime() + (expiresIn * 1000));
        return Jwts.builder().
                claim("userId", userId).
                claim("username", username).
                claim("expireAt", expiration).
                expiration(expiration).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))).
                compact();
    }
}
