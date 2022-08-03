package com.example.repository;

import com.example.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:30
 * @Desc：
 **/
public interface MerchandiseRepository extends CrudRepository<Merchandise, Long>, JpaSpecificationExecutor<Merchandise> {
}
