package com.example.config;

import cn.hutool.core.util.StrUtil;
import com.example.filter.JwtValidInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author： 17602
 * @Date： 2022/8/5 11:20
 * @Desc：
 **/
@EnableScheduling
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:E:/Workspace/JAVA/threeStage/waimai/img_server/");
    }
    
    @Autowired
    private JwtValidInterceptor jwtValidInterceptor;
    @Value(value = "${exclude.url:''}")
    private String excludePath;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] exclude = {"/api/login", "/api/**/register", "/api/captcha/**"};
        if (StrUtil.isNotBlank(excludePath)) {
            exclude = excludePath.split(",");
        }
        registry.addInterceptor(jwtValidInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(exclude);
    }
}
