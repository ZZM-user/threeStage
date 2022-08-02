package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import com.example.mapper.CategoryClassMapper;
import com.example.service.CategoryClassService;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【category_class(商品分类表)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:01
 */
@Service
public class CategoryClassServiceImpl extends ServiceImpl<CategoryClassMapper, CategoryClass>
        implements CategoryClassService {
    /**
     * 分页查询
     *
     * @param categoryClassSearchDTO
     *
     * @return
     */
    @Override
    public IPage<CategoryClass> search(CategoryClassSearchDTO categoryClassSearchDTO) {
        Page<CategoryClass> categoryClassPage = new Page<>(categoryClassSearchDTO.getPage(), categoryClassSearchDTO.getSize());
        return super.baseMapper.search(categoryClassPage, categoryClassSearchDTO);
    }
}
