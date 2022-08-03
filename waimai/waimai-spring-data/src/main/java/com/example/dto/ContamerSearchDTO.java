package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/2 13:00
 * @Desc：
 **/
@ApiModel(value = "会员信息对象")
@Getter
@Setter
public class ContamerSearchDTO extends CreateDateSearchDTO {
    
    @ApiModelProperty(value = "登录名")
    private String loginId;
    @ApiModelProperty(value = "状态(1停用,0正常)")
    private Integer status;
}
