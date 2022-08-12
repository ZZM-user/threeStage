package com.example.controller;

import com.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/8/12 16:51
 * @Desc：
 **/
@RestController
public class RedisController {
    
    /**
     * 键值都是String
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 键值都是Object
     */
    @Autowired
    private RedisTemplate redisTemplate;
    
    // string
    @GetMapping(value = "/redis/set")
    public String strAdd(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        redisTemplate.opsForValue().set("object:" + key, value);
        return "ok";
    }
    
    @PostMapping(value = "/redis/setObject")
    public String objAdd() {
        Employee employee = new Employee();
        employee.setLogin_name("张三");
        employee.setId(1L);
        employee.setLogin_pwd("这是一串密码……");
        redisTemplate.opsForValue().set("employee", employee);
        return "ok";
    }
    
}
