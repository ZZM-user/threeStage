package com.example.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用分类
 */
@Getter
@Setter
public class PageDTO {

    @ApiModelProperty(value = "当前第几页",example = "1")
    private Integer page;

    @ApiModelProperty(value = "每页几笔",example = "10")
    private Integer size;
}
