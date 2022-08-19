package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * 解决java跨域的问题
 */
@Configuration
public class CrosConfig {
    
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 是否允许客户推荐cookie参数
        // response.setHeader("Access-Control-Allow-Credentials", "true");
        corsConfig.setAllowCredentials(true);
        // 允许放行的域名有哪些
        // response.setHeader("Access-Control-Allow-Origin", "*");
        // corsConfig.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfig.setAllowedOriginPatterns(Collections.singletonList("*"));
        // 允许放行的访问方式 GET/POST/PUT/DELETE/OPTIONS.....
        // response.setHeader("Access-Control-Allow-Methods", "*");
        corsConfig.addAllowedMethod(CorsConfiguration.ALL);
        // response.setHeader("Access-Control-Allow-Headers", "*");
        corsConfig.addAllowedHeader(CorsConfiguration.ALL);
        // 默认可不设置这个暴露的头。这个为了安全问题，不能使用*。设置成*，后面会报错：throw new IllegalArgumentException("'*' is not a valid exposed header value");
        // corsConfig.addExposedHeader("");
        corsConfig.setMaxAge(3600L);
        
        //@WebFilter(urlPatterns = "/*")
        // public class CorsFilter implements Filter
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig);
        
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(configSource));
        corsBean.setName("crossOriginFilter");
        corsBean.setOrder(0);// 这个顺序也有可能会有影响，尽量设置在拦截器前面
        return corsBean;
    }
}
