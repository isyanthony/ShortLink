package org.example.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ShortLinkGenerateController {

    @GetMapping("/hello")
    public Mono<String> generate() {
        return Mono.just("Hello World");
    }

}
