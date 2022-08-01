package com.example.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value="分页返回结果")
public class PageVO implements Serializable {
    @ApiModelProperty(value = "共有多少笔数据")
    private Long total;

    @ApiModelProperty(value = "当前页的数据集合")
    private Object rows;
}
