package com.intro.api.configuration;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.service.*;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Value("${swagger.host}")
    private String host;

    private static final String BASE_PACKAGE = "com.intro.api";

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(enabled).host(host).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)).paths(PathSelectors.any()).build()
                .securitySchemes(Collections.singletonList(apiKey())).securityContexts(securityContexts());
    }

    @Bean
    public RequestMappingInfoHandlerMapping requestMapping() {
        return new RequestMappingHandlerMapping();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Intro API interfaces").version("1.0.0").description("APIs").build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!/account/).*$")).build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = { authorizationScope };
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
