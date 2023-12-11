package org.example.algorithm;

/**
 * depend on redis incr operator
 */
public class RedisCodeSender implements ILinkUniqueCode {

    @Override
    public long getUniqueCode() {
        return 0;
    }

    @Override
    public boolean isNormal() {
        return false;
    }

    @Override
    public long retry() {
        return 0;
    }
}
