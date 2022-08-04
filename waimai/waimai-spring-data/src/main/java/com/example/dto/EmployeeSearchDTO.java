package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/2 9:59
 * @Desc： 员工查询
 **/
@ApiModel(value = "员工信息对象")
@Getter
@Setter
public class EmployeeSearchDTO extends PageDTO {
    
    @ApiModelProperty(value = "登录名")
    private String loginName;
    
    @ApiModelProperty(value = "状态(1停用,0正常)", example = "0")
    private Byte status;
}
