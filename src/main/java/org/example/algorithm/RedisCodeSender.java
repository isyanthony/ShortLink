package org.example.algorithm;

import org.springframework.stereotype.Component;

/**
 * depend on redis incr operator
 */
@Component
public class RedisCodeSender implements ILinkUniqueCode {

    @Override
    public long getUniqueCode() {
        return 0;
    }

    @Override
    public boolean isNormal() {
        return false;
    }
}
