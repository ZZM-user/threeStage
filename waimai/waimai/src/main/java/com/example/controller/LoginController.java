package com.example.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.example.common.constants.Waimai;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.LoginUserVO;
import com.example.dto.LoginDTO;
import com.example.service.LoginService;
import com.example.util.ThreadLocalUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author： 17602
 * @Date： 2022/8/15 11:23
 * @Desc：
 **/
@Api(tags = "登录、登出、获取用户信息接口")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LoginService loginService;
    
    @Value(value = "${jwt.signer}")
    private String jwtSinger;
    
    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginDTO loginDTO) {
        String[] redisCaptchaKey = Waimai.getRedisCaptchaKey(loginDTO.getUuid());
        String redisKey = redisCaptchaKey[1];
        
        // 查询redis的值
        String redisValue = (String) redisTemplate.opsForValue().get(redisKey);
        
        if (ObjectUtil.isNull(redisValue) || StrUtil.isBlank(redisValue)) {
            return R.build(AckCode.SMS_CODE_OVERTIME);
        }
        
        // 不管对不对，移除redis中的验证码
        redisTemplate.delete(redisKey);
        
        // 比较
        if (ObjectUtil.isNull(loginDTO.getCode()) || !loginDTO.getCode().equalsIgnoreCase(redisValue)) {
            return R.build(AckCode.SMS_CODE_WRONG);
        }
        // 开始登录
        LoginUserVO dbUser = loginService.login(loginDTO);
        
        // 拿令牌，必须唯一
        // 不带-的uuid
        String uuid = IdUtil.simpleUUID();
    
        String[] redisLoginKey = Waimai.getRedisLoginKey(uuid);
        this.redisTemplate.opsForValue().set(redisLoginKey[1], dbUser, Waimai.REDIS_CAPTCHA_LOGIN_MINUTES, TimeUnit.MINUTES);
    
        // jwt执行操作
        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", redisLoginKey[0]);
        String token = JWTUtil.createToken(map, jwtSinger.getBytes(StandardCharsets.UTF_8));
    
        log.info(loginDTO.getAccount() + "\t登录成功！");
        return R.okHasData(token);
    }
    
    @ApiOperation("获取本地登录用户信息")
    @GetMapping("/user/info")
    public R getUserInfo() {
        LoginUserVO loginUserVO = ThreadLocalUser.loginThreadLocal.get();
        log.info("获取用户信息：" + loginUserVO);
        return R.okHasData(loginUserVO);
    }
}
