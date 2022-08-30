package com.example.controller.front;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.annon.RateLimiter;
import com.example.common.constants.Waimai;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.enums.LimitType;
import com.example.common.validtor.SMSValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author： 17602
 * @Date： 2022/8/30 14:48
 * @Desc： 前端验证码
 **/
@Api(tags = "前端验证码")
@Slf4j
@RequestMapping("/front/sms")
@RestController
public class FrontSmsController {
    
    @Autowired
    RedisTemplate redisTemplate;
    
    
    @RateLimiter(time = 60, count = 2, limitType = LimitType.PARAMS)
    @ApiOperation(value = "发送短信")
    @PostMapping(value = "/send/{phone}")
    public R sendSms(@PathVariable(value = "phone") String phone) {
        R r = this.checkSmsSend(phone);
        if (r.getCode() != 0) {
            return r;
        }
        // 现在不要云调用 SMSSendUtil.sendSms(String phone,String code,Integer minutes)，模拟已发送
        
        // 验证码生成
        Random rd = new Random();
        int code = rd.nextInt(9000) + 1000;
        
        // 发送短信成功
        boolean isSendSms = true;
        log.info(phone + '\t' + code);
        if (isSendSms) {
            String[] redisSMSKey = Waimai.getRedisSMSKey(phone);
            redisTemplate.opsForValue().set(redisSMSKey[1], code, Waimai.SMS_EXPIRED_MINUTES, TimeUnit.MINUTES);
            log.info("验证码发送成功");
            
            return R.ok();
        } else {
            // 短信发送失败
            return R.build(AckCode.SMS_SEND_ERROR);
        }
        
    }
    
    
    @ApiOperation(value = "检查电话号码短信是否已经发送过，还未过期", hidden = true)
    @GetMapping(value = "/checkSmsSend/{phone}")
    public R checkSmsSend(@PathVariable(value = "phone") String phone) {
        String[] redisSMSKey = Waimai.getRedisSMSKey(phone);
        Object code = redisTemplate.opsForValue().get(redisSMSKey[1]);
        log.debug(code + "\t" + phone);
        // 验证码未失效
        if (ObjectUtil.isNull(code)) {
            return R.ok();
        }
        
        return R.build(AckCode.SMS_SEND_COLD_TIME);
        
    }
    
    
    @PostMapping(value = "/validate")
    @ApiOperation(value = "验证手机号验证码是否正确")
    public R validateCode(@RequestBody @Valid SMSValidator smsValidator) {
        String[] redisSMSKey = Waimai.getRedisSMSKey(smsValidator.getPhone());
        Object code = redisTemplate.opsForValue().get(redisSMSKey[1]);
        
        // 验证码失效
        if (ObjectUtil.isNull(code)) {
            return R.build(AckCode.SMS_CODE_OVERTIME);
        }
        // 移除redis中该手机号的验证码
        redisTemplate.delete(smsValidator.getPhone());
        
        // 校验验证码是否正确
        if (!code.toString().equals(smsValidator.getCode())) {
            return R.build(AckCode.SMS_CODE_WRONG);
        }
        return R.ok();
    }
}
