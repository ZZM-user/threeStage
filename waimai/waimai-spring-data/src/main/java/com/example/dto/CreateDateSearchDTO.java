package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author： 17602
 * @Date： 2022/8/3 9:12
 * @Desc： 按时间查询
 **/

@ApiModel(value = "按时间查询")
@Getter
@Setter
public class CreateDateSearchDTO extends PageDTO {
    
    @ApiModelProperty(value = "起始时间(yyyy-MM-dd)", example = "2020-01-08")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd)", example = "2022-10-10")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
