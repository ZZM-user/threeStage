package com.example.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                       .apiInfo(apiInfo())
                       // .enable(false) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                       .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                       .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                       // 配置你想在那个controller层生产接口文档
                       .paths(PathSelectors.any())
                       .build();
    }
    
    // 配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("外卖程序员", "https://www.baidu.com", "xxxx@126.com");
        return new ApiInfo(
                "外卖文档", // 标题
                "外卖前后台api", // 描述
                "v1.0", // 版本
                "", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
    
}
