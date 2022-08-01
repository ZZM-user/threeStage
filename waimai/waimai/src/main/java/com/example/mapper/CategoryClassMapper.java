package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CategoryClass;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【category_class(商品分类表)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:01
* @Entity com.example.entity.CategoryClass
*/
@Mapper
public interface CategoryClassMapper extends BaseMapper<CategoryClass> {

}
