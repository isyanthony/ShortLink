package org.example.algorithm;

/**
 * depend on global code send service
 */
public class CenterCodeSender implements ILinkUniqueCode {

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
