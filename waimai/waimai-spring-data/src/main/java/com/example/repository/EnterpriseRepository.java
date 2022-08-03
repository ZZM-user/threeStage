package com.example.repository;

import com.example.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author： 17602
 * @Date： 2022/8/3 11:24
 * @Desc：
 **/
public interface EnterpriseRepository extends CrudRepository<Enterprise, Long>, JpaSpecificationExecutor<Enterprise> {
}
