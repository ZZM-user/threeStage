package com.example.common.vo;


import com.example.dto.DishFlavorsDTO;
import com.example.entity.Merchandise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/29 9:34
 * @Desc：商品信息输出
 **/
@Setter
@Getter
@ApiModel(value = "商品详情模型")
public class MerchandiseDataVO extends Merchandise {
    
    
    @ApiModelProperty(value = "菜品对应口味列表")
    private List<DishFlavorsDTO> dishFlavors;
}
