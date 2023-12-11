package org.example.controller;


import org.example.common.Result;
import org.example.dto.CreateShortLinkResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/base-api/slink")
public class ShortLinkGenerateController {

    @GetMapping("/create")
    public Mono<Result<CreateShortLinkResp>> createShortLink() {
        CreateShortLinkResp resp = CreateShortLinkResp.builder()
          .url("test")
          .build();
        return Mono.just(Result.ok(resp));
    }

}
