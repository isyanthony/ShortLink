package org.example.service;

import jakarta.annotation.Resource;
import org.example.algorithm.ILinkUniqueCode;
import org.example.dto.ShortLinkGenerateDTO;
import org.example.util.ScaleConvertUtils;
import org.springframework.stereotype.Service;

@Service
public class ShortGenerateService {

    @Resource
    private ILinkUniqueCode linkUniqueCode;

    public ShortLinkGenerateDTO doGenerateShortLink(String link) {
        if (linkUniqueCode.isNormal()) {
            return codeSenderGenerate(link);
        }
        return defaultGenerate(link);

    }

    private ShortLinkGenerateDTO codeSenderGenerate(String link) {
        long uniqueCode = linkUniqueCode.getUniqueCode();
        if (uniqueCode == -1) {
            if (!linkUniqueCode.isNormal()) {
                return defaultGenerate(link);
            }
            uniqueCode = linkUniqueCode.retry();
            if (uniqueCode == -1) {
                return defaultGenerate(link);
            }
        }

        String shortUrl = ScaleConvertUtils._10_to_62(uniqueCode);
        return ShortLinkGenerateDTO.builder()
          .shortUrl(shortUrl)
          .uniqueCode(uniqueCode)
          .build();
    }


    // TODO: default generate algorithm
    private ShortLinkGenerateDTO defaultGenerate(String link) {
        return ShortLinkGenerateDTO.builder()
          .shortUrl(link)
          .uniqueCode(-1L)
          .build();
    }
}
