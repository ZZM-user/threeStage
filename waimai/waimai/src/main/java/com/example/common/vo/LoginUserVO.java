package com.example.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author： 17602
 * @Date： 2022/8/15 14:05
 * @Desc：登录用户
 **/
@Getter
@Setter
@ToString
public class LoginUserVO {
    
    private Long id;
    private String account;
    private String loginPwd;
    private String name;
    private String avatar;
    private Integer status;
    /**
     * 1、管理员
     * 2、商家
     */
    private Integer loginType;
    /**
     * 登录令牌
     */
    private String token;
}
