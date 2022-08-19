package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author： 17602
 * @Date： 2022/8/5 11:20
 * @Desc：
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:E:/Workspace/JAVA/threeStage/waimai/img_server/");
    }
    
}
