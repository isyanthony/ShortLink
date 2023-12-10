package org.example;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import org.example.util.ScaleConvertUtils;
import org.junit.jupiter.api.Test;

public class ShortLinkGenerateTest {

    @Test
    public void murmurHashTest() {
        var hashFunction = Hashing.murmur3_128();
        HashCode hashCode = hashFunction.hashString("https://www.baidu.com?ew123=1231&312312=31231&ew123=1231&312312=31231&ew123=1231&312312=31231&ew123=1231&312312=31231&ew123=1231&312312=31231&ew123=1231&312312=31231", Charset.defaultCharset());
        System.out.println(hashCode.hashCode());
        System.out.println(ScaleConvertUtils._10_to_62(hashCode.hashCode()));
    }

}
