package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/5 14:02
 * @Desc： 汽车经销商搜索模型
 **/
@ApiModel(value = "搜索模型")
@Getter
@Setter
public class DscDealerSearchDTO extends PageDTO {
    @ApiModelProperty(value = "经销商名称", example = "中山市长安汽车")
    private String dealerName;
    
    @ApiModelProperty(value = "大区名称", example = "特别大区")
    private String orgName;
    
    @ApiModelProperty(value = "电话")
    private String phone;
}
