package com.sparkals.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class redisTest {
@Autowired
    private RedisTemplate redisTemplate;

@Disabled
@Test
    void testSendMail(){
   redisTemplate.opsForValue().set("email","ayushr2023@gmail.com");
  Object salary =  redisTemplate.opsForValue().get("salary");
  int a  = 1;
}
}

