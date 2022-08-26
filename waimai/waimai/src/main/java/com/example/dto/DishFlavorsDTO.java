package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/26 16:29
 * @Desc：菜品口味对象
 **/
@Getter
@Setter
@ApiModel("菜品口味对象")
public class DishFlavorsDTO {
    
    @ApiModelProperty(hidden = true)
    private String inputValue;
    
    @ApiModelProperty(hidden = true)
    private Boolean inputVisible;
    
    @ApiModelProperty(value = "口味名称")
    private String name;
    
    @ApiModelProperty(value = "口味tags")
    private String[] tags;
}
