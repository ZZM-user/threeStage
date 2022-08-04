package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/2 13:51
 * @Desc： 商品信息
 **/
@ApiModel(value = "商品信息对象")
@Getter
@Setter
public class MerchandiseSearchDTO extends PageDTO {
    @ApiModelProperty(value = "商家id")
    private Long eid;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "是否上架 1：上架 ，0：没上架", example = "1")
    private Integer isgrounding;
    @ApiModelProperty(value = "账号类型(普通用户：0,管理员：1)", example = "1")
    private Integer accountType;
}
