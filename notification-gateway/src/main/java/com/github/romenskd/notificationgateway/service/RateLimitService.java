package com.github.romenskd.notificationgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${rate.limit.max.requests}")
    private int maxLimit;

    @Value("${rate.limit.window.seconds}")
    private int timeWindowSeconds;

    public boolean isRequestAllowed(String userId) {
        String key = "rate_limit:" + userId;

        Long requestCount = redisTemplate.opsForValue().increment(key);

        if (requestCount == null || requestCount == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(timeWindowSeconds));
        }

        return requestCount != null && requestCount <= maxLimit;
    }
}
