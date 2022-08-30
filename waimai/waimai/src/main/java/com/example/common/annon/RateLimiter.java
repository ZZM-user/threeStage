package com.example.common.annon;

import com.example.common.constants.Waimai;
import com.example.common.enums.LimitType;

import java.lang.annotation.*;

/**
 * @Author： 17602
 * @Date： 2022/8/30 18:45
 * @Desc： 限流
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    public String key() default Waimai.RATE_LIMIT_KEY;
    
    /**
     * 限流时间,单位秒
     */
    int time() default 60;
    
    /**
     * 限流次数
     */
    int count() default 100;
    
    /**
     * 限流类型 (生成redisKey,默认就是方法名，ip:方法名+ip, params:方法名+params)
     */
    LimitType limitType() default LimitType.DEFAULT;
}
