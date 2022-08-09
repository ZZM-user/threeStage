package com.example.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Author： 17602
 * @Date： 2022/8/9 22:02
 * @Desc：
 **/
@ApiModel("登录名验证是否使用")
@Getter
@Setter
public class LoginIdValidatorDTO {
    private Long id;
    
    @NotNull(message = "该值不能为空")
    private String loginId;
}
