package com.sparkals.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkals.journalApp.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> response) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), response);
        } catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }
    public void set(String key, Object o, long ttl) {
        try {
            redisTemplate.opsForValue().set(key, o.toString(),ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}

