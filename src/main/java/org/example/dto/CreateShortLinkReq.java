package org.example.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateShortLinkReq {

    private String host;

    private String link;

    private Long operator;

    private Long expireTime;
}
