package com.example.common.constants;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;

/**
 * @Author： 17602
 * @Date： 2022/8/15 9:42
 * @Desc：
 **/
public final class Waimai {
    
    /**
     * 验证码前缀
     */
    public static final String REDIS_CAPTCHA = "captcha:";
    /**
     * 验证码过期时间  分钟
     */
    public static final int REDIS_CAPTCHA_EXPIRED_MINUTES = 3;
    /**
     * 登陆成功后缀
     */
    public static final String REDIS_LOGIN_KEY = "login_success:";
    /**
     * 有效时间
     */
    public static final int REDIS_CAPTCHA_LOGIN_MINUTES = 120;
    
    /**
     * 短信前缀
     */
    public static final String SMS_KEY = "sms:";
    public static final String RATE_LIMIT_KEY = "sms_times:";
    /**
     * 有效时间
     */
    public static final int SMS_EXPIRED_MINUTES = 2;
    
    /**
     * 获取redis的catpcha的键值
     *
     * @return [0]=uuid,[1]=redis完整的key
     */
    public static String[] getRedisCaptchaKey(String uuid) {
        if (StrUtil.isBlank(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        String redisKey = REDIS_CAPTCHA + uuid;
        return new String[]{uuid, redisKey};
    }
    
    /**
     * 生成redis的登录键
     *
     * @param uuid
     *
     * @return
     */
    public static String[] getRedisLoginKey(String uuid) {
        if (StrUtil.isBlank(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        String redisKey = REDIS_LOGIN_KEY + uuid;
        return new String[]{uuid, redisKey};
    }
    
    /**
     * 获取redis的SMS的键值
     *
     * @return [0]=phone,[1]=redis完整的key
     */
    public static String[] getRedisSMSKey(String phone) {
        
        String redisKey = SMS_KEY + phone;
        return new String[]{phone, redisKey};
    }
    
}
