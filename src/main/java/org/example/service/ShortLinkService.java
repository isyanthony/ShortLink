package org.example.service;


import jakarta.annotation.Resource;
import org.example.algorithm.ILinkUniqueCode;
import org.example.util.ScaleConvertUtils;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkService {

    @Resource
    private ILinkUniqueCode linkUniqueCode;

    public String convertToShortLink(String url, String host) {
        if (linkUniqueCode.isNormal()) {
            return defaultConvert();
        }
        String shortLink;

        long uniqueCode = linkUniqueCode.getUniqueCode();
        String postfix = ScaleConvertUtils._10_to_62(uniqueCode);
        if (!host.endsWith("/")) {
            shortLink = host + "/" + postfix;
        } else {
            shortLink = host + postfix;
        }

        // TODO 数据落库
        return shortLink;
    }

    private String defaultConvert() {
        return "";
    }

}
