package com.snapp.expense_tracker.user.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RefreshTokenService {
    private final String AUTHENTICATION_REFRESH_PREFIX = "authentication:refresh:";
    private final StringRedisTemplate redisTemplate;

    public RefreshTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(Long userId, String refreshToken, long durationMs) {
        String key = AUTHENTICATION_REFRESH_PREFIX + userId;
        redisTemplate.opsForValue().set(key, refreshToken, durationMs, TimeUnit.MILLISECONDS);
    }

    public boolean validateRefreshToken(Long userId, String refreshToken) {
        String key = AUTHENTICATION_REFRESH_PREFIX + userId;
        String storedToken = redisTemplate.opsForValue().get(key);
        return storedToken != null && storedToken.equals(refreshToken);
    }

    public void deleteRefreshToken(Long userId) {
        String key = AUTHENTICATION_REFRESH_PREFIX + userId;
        redisTemplate.delete(key);
    }
}
