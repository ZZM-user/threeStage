package com.example.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.common.constants.Waimai;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.LoginUserVO;
import com.example.util.ThreadLocalUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @Author： 17602
 * @Date： 2022/8/16 10:44
 * @Desc： JWT token验证
 **/
@Component
@Slf4j
public class JwtValidInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Value(value = "${jwt.signer}")
    private String jwtSigner;
    
    @Value(value = "${jwt.header}")
    private String jwtHeader;
    private final String[] whiteList = {"/api/login", "/api/register", "/404"};
    
    @Override
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("校验请求" + request.getRequestURI());
        // Authorization
        // 取出请求头中的验证token
        String authorization = request.getHeader(jwtHeader);
        if (StrUtil.isBlank(authorization)) {
            log.error("未携带验证");
            printJson(response, AckCode.SYSTEM_TOKEN_FAIL);
            return false;
        }
        
        // 检查token是否能被正确解密
        boolean verify = JWTUtil.verify(authorization, jwtSigner.getBytes(StandardCharsets.UTF_8));
        if (!verify) {
            log.error("验证码无效");
            printJson(response, AckCode.SYSTEM_TOKEN_FAIL);
            return false;
        }
        // 取出其中预先存储的uuid
        JWT jwt = JWTUtil.parseToken(authorization);
        String uuid = jwt.getPayload("uuid").toString();
        
        // 通过redis查询校验token有效性
        String[] redisLoginKey = Waimai.getRedisLoginKey(uuid);
        LoginUserVO loginUserVO = (LoginUserVO) this.redisTemplate.opsForValue().get(redisLoginKey[1]);
        if (ObjectUtil.isNull(loginUserVO)) {
            log.error("验证码失效");
            printJson(response, AckCode.TOKEN_EXPIRED);
            return false;
        }
        
        ThreadLocalUser.loginThreadLocal.set(loginUserVO);
        log.info("放行请求：" + request.getRequestURL());
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalUser.loginThreadLocal.remove();
    }
    
    /**
     * 输出json
     *
     * @param response
     * @param ackCode
     */
    private void printJson(HttpServletResponse response, AckCode ackCode) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        
        try {
            PrintWriter out = response.getWriter();
            
            R r = R.build(ackCode);
            // r转json
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(r);
            // 输出json
            out.println(jsonStr);
            
            out.flush();
            out.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
