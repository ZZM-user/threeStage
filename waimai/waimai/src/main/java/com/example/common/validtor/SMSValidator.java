package com.example.common.validtor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ApiModel(value = "验证手机号码模型")
public class SMSValidator {
    
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(?:0|86|\\+86)?1[3-9]\\d{9}$", message = "格式有错误")
    @ApiModelProperty(value = "手机号码", required = true)
    private String phone;
}
