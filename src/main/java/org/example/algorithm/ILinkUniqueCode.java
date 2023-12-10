package org.example.algorithm;

public interface ILinkUniqueCode {

    /**
     * get unique code for generate short link
     * @return unique code
     */
    long getUniqueCode();

    /**
     * verify service status
     * @return normal or not
     */
    boolean isNormal();
}
