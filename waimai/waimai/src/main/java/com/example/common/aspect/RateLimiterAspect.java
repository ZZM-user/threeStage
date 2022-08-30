package com.example.common.aspect;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import com.example.common.WaimaiException;
import com.example.common.annon.RateLimiter;
import com.example.common.enums.AckCode;
import com.example.common.enums.LimitType;
import com.example.util.IpUtils;
import com.example.util.ServletUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author： 17602
 * @Date： 2022/8/30 18:46
 * @Desc： 限流实现
 **/
@Log4j2
@Component
@Aspect
public class RateLimiterAspect {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    /**
     * 注入一个redis限流脚本对象
     */
    @Autowired
    private RedisScript<Long> limitScript;
    
    
    /**
     * 前置增强
     *
     * @param point       当前执行的方法是什么
     * @param rateLimiter 自定义注解
     *
     * @throws Throwable
     */
    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        // 前缀 rate_limit:
        String key = rateLimiter.key();
        // 时间
        int time = rateLimiter.time();
        // 次数
        int count = rateLimiter.count();
        
        String combineKey = getCombineKey(rateLimiter, point);
        List<Object> keys = Collections.singletonList(combineKey);
        
        // 脚本有问题
        Object value = redisTemplate.execute(limitScript, keys, count, time);
        Long number = (Long) value;
        if (Objects.isNull(number) || number.intValue() > count) {
            throw new WaimaiException(AckCode.RATE_LIMATE);
        }
        log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), combineKey);
        
    }
    
    /**
     * redis key到底用什么组装，默认，ip,params
     *
     * @param rateLimiter
     * @param point
     *
     * @return
     */
    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        
        // 获取当前方法名称
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        
        // 如果IP限流，取得当前IP地址
        if (rateLimiter.limitType() == LimitType.IP) {
            stringBuffer.append("-").append(IpUtils.getIpAddr(ServletUtils.getRequest()));
        } else if (rateLimiter.limitType() == LimitType.PARAMS) {
            // 参数限流
            int count = method.getParameterCount();
            // 得到参数值数组
            Object[] args = point.getArgs();
            String value = "";
            if (count == 1) {
                value = args[0].toString();
                stringBuffer.append("-").append(value);
            } else if (count > 1) {
                // json转字符串   --> md5加密
                String jsonStr = JSONUtil.toJsonStr(args);
                value = MD5.create().digestHex(jsonStr);
                stringBuffer.append("-").append(value);
            }
        }
        
        return stringBuffer.toString();
    }
    
}
