package org.example.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public class EncryptUtils {

    public static String md5_32(String s) {
        Assert.notNull(s, "encrypt target cannot be null value.");

        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte value : b) {
                i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 encode error, origin: {}, error: {}", s, e.getMessage());
        }
        return result;
    }

    public static String md5_16(String s) {
        return md5_32(s).substring(8, 24);
    }

}
