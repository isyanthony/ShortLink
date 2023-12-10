package org.example;


import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    @Resource
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}