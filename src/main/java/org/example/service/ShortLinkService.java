package org.example.service;


import jakarta.annotation.Resource;
import org.example.dto.CreateShortLinkReq;
import org.example.dto.CreateShortLinkResp;
import org.example.dto.ShortLinkGenerateDTO;
import org.example.model.LinkDetail;
import org.example.model.LinkToShort;
import org.example.respsitory.LinkDetailRepository;
import org.example.respsitory.LinkToShortRepository;
import org.example.util.EncryptUtils;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkService {

    @Resource
    private ShortGenerateService shortGenerateService;
    @Resource
    private LinkToShortRepository linkToShortRepository;
    @Resource
    private LinkDetailRepository linkDetailRepository;

    public CreateShortLinkResp convertToShortLink(CreateShortLinkReq req) {
        ShortLinkGenerateDTO linkDto = shortGenerateService.doGenerateShortLink(req.getLink());
        // TODO 数据落库
        String linkMd5 = EncryptUtils.md5_32(req.getLink());
        LinkDetail detail = LinkDetail.of(linkDto.getUniqueCode(),
                                          req.getOperator(),
                                          req.getHost(),
                                          req.getLink(),
                                          linkMd5,
                                          linkDto.getShortUrl(),
                                          req.getExpireTime()
        );

        // save logic need to add retry strategy, need split into service and db operator
        LinkDetail saveResult = linkDetailRepository.save(detail);
        Long id = saveResult.getId();
        if (id == null) {
            throw new RuntimeException("save error");
        }

        LinkToShort linkToShort = LinkToShort.of(linkDto.getShortUrl(), linkMd5);
        LinkToShort mpResult = linkToShortRepository.save(linkToShort);
        if (mpResult.getId() == null) {
            throw new RuntimeException("save error");
        }

        return CreateShortLinkResp.builder()
          .url(linkDto.getShortUrl())
          .build();
    }

    private String defaultConvert() {
        return "";
    }

}
