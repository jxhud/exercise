package com.wq.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setCode(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 7200, TimeUnit.SECONDS);
    }
    public String getCode(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }
    public boolean hasCode(String key) {
        return stringRedisTemplate.hasKey(key);
    }
}
