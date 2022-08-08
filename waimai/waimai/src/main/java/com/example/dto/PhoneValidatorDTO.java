package com.example.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author： 17602
 * @Date： 2022/8/8 10:49
 * @Desc：
 **/
@ApiModel("电话号验证是否使用")
@Getter
@Setter
public class PhoneValidatorDTO {
    
    private Long id;
    
    @NotNull(message = "该值不能为空")
    @Pattern(regexp = "^0?(13|15|17|18|14)[0-9]{9}$", message = "电话格式有误")
    private String Phone;
}
