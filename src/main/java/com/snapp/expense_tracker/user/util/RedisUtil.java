package com.snapp.expense_tracker.user.util;

import com.snapp.expense_tracker.user.exception.InvalidRefreshTokenException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
    private final String AUTHENTICATION_REFRESH_PREFIX = "authentication:refresh:";
    private final StringRedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(Long userId, String refreshToken, long durationMs) {
        String key = AUTHENTICATION_REFRESH_PREFIX + userId;
        redisTemplate.opsForValue().set(key, refreshToken, durationMs, TimeUnit.MILLISECONDS);
    }

    public void validateRefreshToken(Long userId, String refreshToken) {
        String key = AUTHENTICATION_REFRESH_PREFIX + userId;
        String storedToken = redisTemplate.opsForValue().get(key);
        if (storedToken == null || !storedToken.equals(refreshToken))
            throw new InvalidRefreshTokenException();
    }

    public void deleteRefreshToken(Long userId) {
        String key = AUTHENTICATION_REFRESH_PREFIX + userId;
        redisTemplate.delete(key);
    }
}
