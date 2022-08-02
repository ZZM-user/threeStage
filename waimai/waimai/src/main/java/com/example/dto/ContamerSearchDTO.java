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
public class ContamerSearchDTO extends PageDTO {
    
    @ApiModelProperty(value = "登录名", example = "test")
    private String loginId;
    @ApiModelProperty(value = "状态(1停用,0正常)", example = "1")
    private Byte status;
    @ApiModelProperty(value = "创建日期（起始）", example = "2000-10-12")
    private String startDate;
    @ApiModelProperty(value = "创建日期（结束）", example = "2030-10-03")
    private String endDate;
}
