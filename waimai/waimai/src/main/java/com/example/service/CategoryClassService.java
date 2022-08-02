package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;

/**
 * @author 17602
 * @description 针对表【category_class(商品分类表)】的数据库操作Service
 * @createDate 2022-08-01 14:06:01
 */
public interface CategoryClassService extends IService<CategoryClass> {
    /**
     * 分页查询
     *
     * @param page
     * @param categoryClassSearchDTO
     *
     * @return
     */
    IPage<CategoryClass> search(CategoryClassSearchDTO categoryClassSearchDTO);
    
}
