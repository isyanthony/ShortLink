package org.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * link detail
 * @author jingyang
 * @date : 2023-12-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(value = "link detail")
@Table(name = "link_detail")
public class LinkDetail implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    @ApiModelProperty(name = "主键")
    private Long id;
    /** 发号器 | -1 兜底策略 */
    @ApiModelProperty(name = "发号器 | -1 兜底策略")
    private Long code;
    /** 操作人uid */
    @ApiModelProperty(name = "操作人uid")
    private Long operator;
    /** 协议 http \ https */
    @ApiModelProperty(name = "协议 http | https")
    private String protocol;
    /** 短链接域名 */
    @ApiModelProperty(name = "短链接域名")
    private String host;
    /** 短链接 */
    @ApiModelProperty(name = "短链接")
    private String shortLink;
    /** 长链接 */
    @ApiModelProperty(name = "长链接")
    private String link;
    /** 长链接MD5 */
    @ApiModelProperty(name = "长链接MD5")
    private String linkMd5;
    /** -1 | 0 | 1，删除 | 禁用 | 启用 */
    @ApiModelProperty(name = "-1 | 0 | 1，删除 | 禁用 | 启用")
    private Integer status;
    /** 过期时间 | -1 永久 */
    @ApiModelProperty(name = "过期时间 | -1 永久")
    private Long expireTime;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间")
    private Long createTime;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间")
    private Long updateTime;

    public static LinkDetail of(Long code, Long operator, String host, String link, String linkMd5, String shortLink, Long expireTime) {
        String protocol = link.startsWith("https") ? "https" : "http";
        long timestamp = System.currentTimeMillis();
        return LinkDetail.builder()
          .code(code)
          .protocol(protocol)
          .host(host)
          .link(link)
          .linkMd5(linkMd5)
          .shortLink(shortLink)
          .operator(operator)
          .expireTime(expireTime)
          .updateTime(timestamp)
          .createTime(timestamp)
          .status(0)
          .build();
    }

    @Override
    public LinkDetail clone() {
        try {
            LinkDetail clone = (LinkDetail) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}