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
 * 长链接映射;
 * @author : http://www.chiner.pro
 * @date : 2023-12-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(value = "长链接映射", description = "")
@Table(name = "link_to_short")
public class LinkToShort implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    @ApiModelProperty
    private Long id;
    /** 短链接 */
    @ApiModelProperty(name = "短链接")
    private String shortUrl;
    /** 长链接 MD5 */
    @ApiModelProperty(name = "长链接 MD5")
    private String linkMd5;

    public static LinkToShort of(String shortUrl, String linkMd5) {
        return LinkToShort.builder()
          .shortUrl(shortUrl)
          .linkMd5(linkMd5)
          .build();
    }

    @Override
    public LinkToShort clone() {
        try {
            LinkToShort clone = (LinkToShort) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}