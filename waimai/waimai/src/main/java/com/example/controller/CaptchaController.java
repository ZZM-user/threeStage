package com.example.controller;

import com.example.common.constants.Waimai;
import com.example.common.domain.R;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author： 17602
 * @Date： 2022/8/15 9:19
 * @Desc： 验证码
 **/
@Api(tags = "图形验证码")
@CrossOrigin
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @ApiOperation("英数验证")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "width", dataType = "Integer", dataTypeClass = Integer.class, value = "图片宽度", defaultValue = "150"),
            @ApiImplicitParam(name = "height", dataType = "Integer", dataTypeClass = Integer.class, value = "图片高度", defaultValue = "50"),
            @ApiImplicitParam(name = "len", dataType = "Integer", dataTypeClass = Integer.class, value = "验证码长度", defaultValue = "4"),
    })
    @GetMapping("/png")
    public R SpecCaptcha(@RequestParam(value = "width", defaultValue = "140") int width,
                         @RequestParam(value = "height", defaultValue = "80") int height,
                         @RequestParam(value = "len", defaultValue = "4") int len) {
        
        SpecCaptcha captcha = new SpecCaptcha(width, height, len);
        String value = captcha.text();
        String img = captcha.toBase64();
        
        String[] redisCaptchaKey = Waimai.getRedisCaptchaKey(null);
        redisTemplate.opsForValue().set(redisCaptchaKey[1], value, Waimai.REDIS_CAPTCHA_EXPIRED_MINUTES, TimeUnit.MINUTES);
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", redisCaptchaKey[0]);
        map.put("img", img);
        return R.okHasData(map);
    }
    
    @ApiOperation("算数验证")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "width", dataType = "Integer", dataTypeClass = Integer.class, value = "图片宽度", defaultValue = "150"),
            @ApiImplicitParam(name = "height", dataType = "Integer", dataTypeClass = Integer.class, value = "图片高度", defaultValue = "50"),
            @ApiImplicitParam(name = "len", dataType = "Integer", dataTypeClass = Integer.class, value = "验证码长度", defaultValue = "2"),
    })
    @GetMapping("/arithmetic")
    public R ArithmeticCaptcha(@RequestParam(value = "width", defaultValue = "140") int width,
                               @RequestParam(value = "height", defaultValue = "80") int height,
                               @RequestParam(value = "len", defaultValue = "2") int len) {
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(width, height, len);
        // 获取运算的公式：3+2=?
        String arithmeticString = captcha.getArithmeticString();
        // 获取运算的结果：5
        String arithmeticResult = captcha.text();
        String img = captcha.toBase64();
        
        String[] redisCaptchaKey = Waimai.getRedisCaptchaKey(null);
        redisTemplate.opsForValue().set(redisCaptchaKey[1], arithmeticResult, Waimai.REDIS_CAPTCHA_EXPIRED_MINUTES, TimeUnit.MINUTES);
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", redisCaptchaKey[0]);
        map.put("img", img);
        return R.okHasData(map);
    }
    
    @ApiOperation("中文验证")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "width", dataType = "Integer", dataTypeClass = Integer.class, value = "图片宽度", defaultValue = "150"),
            @ApiImplicitParam(name = "height", dataType = "Integer", dataTypeClass = Integer.class, value = "图片高度", defaultValue = "50"),
            @ApiImplicitParam(name = "len", dataType = "Integer", dataTypeClass = Integer.class, value = "验证码长度", defaultValue = "4"),
    })
    @GetMapping("/chinese")
    public R ChineseCaptcha(@RequestParam(value = "width", defaultValue = "140") int width,
                            @RequestParam(value = "height", defaultValue = "80") int height,
                            @RequestParam(value = "len", defaultValue = "4") int len) {
        // 中文类型
        ChineseCaptcha captcha = new ChineseCaptcha(130, 48);
        // 获取运算的公式：3+2=?
        // 获取运算的结果：5
        String text = captcha.text();
        String img = captcha.toBase64();
        
        String[] redisCaptchaKey = Waimai.getRedisCaptchaKey(null);
        redisTemplate.opsForValue().set(redisCaptchaKey[1], text, Waimai.REDIS_CAPTCHA_EXPIRED_MINUTES, TimeUnit.MINUTES);
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", redisCaptchaKey[0]);
        map.put("img", img);
        return R.okHasData(map);
    }
    
    
}
