package com.example.dto;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Author： 17602
 * @Date： 2022/8/15 11:18
 * @Desc：
 **/
@Getter
@Setter
@ApiModel("登录验证")
public class LoginDTO {
    
    @NotBlank(message = "账号不能为空")
    private String account;
    
    @NotBlank(message = "密码不能为空")
    private String loginPwd;
    
    /**
     * 验证码
     */
    @NotBlank(message = "验证码标识不能为空")
    private String uuid;
    
    @NotBlank(message = "验证码不能为空")
    private String code;
}
