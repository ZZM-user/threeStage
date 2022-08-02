package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/2 13:27
 * @Desc： 菜单分类
 **/
@ApiModel(value = "菜单信息对象")
@Getter
@Setter
public class CategoryClassSearchDTO extends PageDTO {
    @ApiModelProperty(value = "商家id", example = "1000")
    private Integer eid;
    @ApiModelProperty(value = "餐品分类名", example = "盖浇饭")
    private String name;
    @ApiModelProperty(value = "账号类型(普通用户：0,管理员：1)", example = "1")
    private Integer accountType;
}
