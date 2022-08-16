package com.example.config;

import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                       // 将api的元信息设置为包含在json ResourceListing响应中。
                       .apiInfo(apiInfo())
                       // 选择哪些接口作为swagger的doc发布
                       .select()
                       .apis(RequestHandlerSelectors.any())
                       .paths(PathSelectors.any())
                       .build()
                
                       // 支持的通讯协议集合
                       .protocols(newHashSet("https", "http"))
                
                       // 授权信息设置，必要的header token等认证信息
                       .securitySchemes(securitySchemes())
                
                       // 授权信息全局应用
                       .securityContexts(securityContexts());
    }
    
    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("外卖 Api Doc")
                       .description("")
                       .contact(new Contact("lighter", null, "123456@gmail.com"))
                       .version("Application Version: " + "" + ", Spring Boot Version: " + SpringBootVersion.getVersion())
                       .build();
    }
    
    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("", "Authorization", "header");
        return Collections.singletonList(apiKey);
    }
    
    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }
    
    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
    
}
