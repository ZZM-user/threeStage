package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/2 11:50
 * @Desc：
 **/
@ApiModel(value = "商家信息对象")
@Getter
@Setter
public class EnterpriseSearchDTO extends PageDTO {
    
    @ApiModelProperty(value = "商家名称", example = "蜜雪冰城")
    private String name;
    @ApiModelProperty(value = "商家电话", example = "1351xxxxxxx")
    private String phone;
    @ApiModelProperty(value = "商家状态(1停用,0正常)", example = "0")
    private Byte status;
    @ApiModelProperty(value = "创建日期（起始）", example = "2020-01-08")
    private String startDate;
    @ApiModelProperty(value = "创建日期（结束）", example = "2022-10-10")
    private String endDate;
}
