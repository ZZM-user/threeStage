package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author： 17602
 * @Date： 2022/8/2 9:49
 * @Desc： 基本分页类
 **/

@ApiModel(value = "分页对象")
@Getter
@Setter
public class PageDTO implements Serializable {
    
    @ApiModelProperty(name = "page", value = "当前页数", required = true, example = "1")
    private Integer page;
    
    @ApiModelProperty(name = "size", value = "每页展示数量", required = true, example = "10")
    private Integer size;
}
