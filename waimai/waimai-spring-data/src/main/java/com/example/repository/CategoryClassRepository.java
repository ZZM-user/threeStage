package com.example.repository;

import com.example.entity.CategoryClass;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:14
 * @Desc：
 **/
public interface CategoryClassRepository extends CrudRepository<CategoryClass, Long>, JpaSpecificationExecutor<CategoryClass> {
}
