package org.example;


import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableConfigurationProperties
@EnableJpaRepositories
@SpringBootApplication
public class Main {

    @Resource
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}