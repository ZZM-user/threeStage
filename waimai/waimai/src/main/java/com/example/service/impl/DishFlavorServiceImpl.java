package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.DishFlavor;
import com.example.mapper.DishFlavorMapper;
import com.example.service.DishFlavorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 17602
 * @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
        implements DishFlavorService {
    
    /**
     * 根据商品id查询口味
     *
     * @param id
     *
     * @return
     */
    @Override
    public List<DishFlavor> findByDishId(Long id) {
        QueryWrapper<DishFlavor> wrapper = new QueryWrapper<>();
        wrapper.eq("dish_id", id);
        wrapper.eq("is_deleted", 0);
        return this.baseMapper.selectList(wrapper);
    }
    
    /**
     * 根据商品id删除
     *
     * @param id
     */
    @Override
    public void removeByDishId(Long id) {
        QueryWrapper<DishFlavor> wrapper = new QueryWrapper<>();
        wrapper.eq("dish_id", id);
        wrapper.eq("is_deleted", 0);
        this.baseMapper.delete(wrapper);
    }
}
