package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:02
* @Entity com.example.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
