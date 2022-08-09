package com.example.filter;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author： 17602
 * @Date： 2022/7/28 14:36
 * @Desc： 打印请求日志
 **/
@Order(0)
// 如果我们有多个过滤器，那这个Order就可以指定优先级
@Slf4j
@Component
// 这里可以使用@Compent，也可以不加这个@Component，而是在@SpringBootApplication入口类上新增注解@ServletComponentScan。@Component也可以替换成@Configration
@WebFilter(urlPatterns = "/*", filterName = "RecordFilter")
public class RecordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("RecordFilter 启动……");
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("收到请求："
                         + request.getServerName()
                         + " " + request.getMethod()
                         + " " + request.getRequestURI()
        );
        if (ObjectUtil.isNotNull(request.getQueryString())) {
            log.info("详细参数： " + Arrays.toString(request.getQueryString().split("&")));
        }
        /* body只能被读取一次，controller就读不到了，有解决办法，但是也不想再平生事端了 */
        // if ("POST".equalsIgnoreCase(request.getMethod())) {
        //     String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //     log.info("携带请求体：" + body);
        // }
    
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void destroy() {
        log.info("RecordFilter 销毁……");
    }
}
