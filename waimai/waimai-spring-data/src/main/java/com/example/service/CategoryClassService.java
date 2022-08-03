package com.example.service;

import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import org.springframework.data.domain.Page;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:13
 * @Desc：
 **/
public interface CategoryClassService {
    Page<CategoryClass> search(CategoryClassSearchDTO categoryClassSearchDTO);
}
