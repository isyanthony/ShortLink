package org.example.algorithm;


import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class CodeSenderAutoConfiguration {

    @Resource
    private CodeSenderConfigProperties configProperties;
    @Bean
    @ConditionalOnProperty(prefix = "link.sender", name = "impl", havingValue = "center")
    public ILinkUniqueCode centerCodeSender() {
        System.out.println(configProperties);
       return new CenterCodeSender();
    }

    @Bean
    @ConditionalOnProperty(prefix = "link.sender", name = "impl", havingValue = "redis")
    public ILinkUniqueCode redisCodeSender() {
        return new RedisCodeSender();
    }

}
