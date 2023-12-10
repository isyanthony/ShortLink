package org.example.algorithm;

import org.springframework.stereotype.Component;

/**
 * depend on global code send service
 */
@Component
public class CenterCodeSender implements ILinkUniqueCode {

    @Override
    public long getUniqueCode() {
        return 0;
    }

    @Override
    public boolean isNormal() {
        return false;
    }
}
