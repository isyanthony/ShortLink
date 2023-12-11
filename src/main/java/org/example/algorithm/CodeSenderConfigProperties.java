package org.example.algorithm;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "link.sender")
@Data
public class CodeSenderConfigProperties {

    /**
     * code sender impl: redis | center
     */
    private String impl;
    /**
     * request timeout
     */
    private Integer timeout;

    /**
     * retry times
     */
    private Integer retry;
}
