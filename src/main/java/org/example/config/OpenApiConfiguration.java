package org.example.config;

import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class OpenApiConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
          .apiInfo(apiInfo())
          .select()
          .apis(apis())
          .paths(PathSelectors.any())
          .build()
          .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
          .title("ShortLink")
          .description("ShortLink, convert long link to short link")
          .version("1.0.0")
          .license("jingyang")
          .build();
    }

    private Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage("org.example.controller");
    }

}
