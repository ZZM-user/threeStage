package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/11 21:51
 * @Desc：
 **/
@ApiModel("汽车搜索模型")
@Getter
@Setter
public class CarTrainInfoSearchDTO extends PageDTO {
    
    @ApiModelProperty(value = "搜索关键字", example = "长安")
    private String keyword;
}
