package com.example.repository;

import com.example.entity.Contamer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:43
 * @Desc：
 **/
public interface ContamerRepository extends CrudRepository<Contamer, Long>, JpaSpecificationExecutor<Contamer> {
}
