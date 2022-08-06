package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 17602
 * @description 针对表【category_class(商品分类表)】的数据库操作Mapper
 * @createDate 2022-08-01 14:06:01
 * @Entity com.example.entity.CategoryClass
 */
@Mapper
public interface CategoryClassMapper extends BaseMapper<CategoryClass> {
    
    /**
     * 分页查询-管理员版
     *
     * @param page
     * @param categoryClassSearchDTO
     *
     * @return
     */
    IPage<CategoryClass> search(IPage<?> page, @Param("cate") CategoryClassSearchDTO categoryClassSearchDTO);
    
    /**
     * 判断是否包含重复
     *
     * @param categoryClass
     *
     * @return
     */
    List<CategoryClass> hasRepeat(CategoryClass categoryClass);
}
