package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.DishFlavor;

import java.util.List;

/**
 * @author 17602
 * @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service
 * @createDate 2022-08-01 14:06:02
 */
public interface DishFlavorService extends IService<DishFlavor> {
    
    /**
     * 根据商品id查询口味
     *
     * @param id
     *
     * @return
     */
    List<DishFlavor> findByDishId(Long id);
    
    /**
     * 根据商品id删除
     *
     * @param id
     */
    void removeByDishId(Long id);
}
