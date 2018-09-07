/*
package com.example.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;


   */
/* @Test
    public void contextLoads() {

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("1", "a");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("请求redis100次");
        int i = 0;
        while (i++ < 100) {
            Random random = new Random();
            valueOperations.set(random + "", "a");
            System.out.println(valueOperations.get(random + ""));
            System.out.println(i);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }*//*




   @Test
    public void contextLoads() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("请求redis100次");
        int i = 0;
        while (i++ < 100) {
            RLock lock = redissonClient.getLock("aax");
            lock.lock();
            lock.unlock();
            System.out.println(i);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
*/
