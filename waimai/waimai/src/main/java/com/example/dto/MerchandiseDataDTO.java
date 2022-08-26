package com.example.dto;

import com.example.entity.Merchandise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/26 16:28
 * @Desc：商品信息保存实体
 **/
@Getter
@Setter
@ApiModel(value = "商品信息保存实体")
public class MerchandiseDataDTO extends Merchandise {
    
    
    @ApiModelProperty(value = "菜品对应口味列表")
    private List<DishFlavorsDTO> dishFlavors;
}
