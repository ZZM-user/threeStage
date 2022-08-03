package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author： 17602
 * @Date： 2022/8/3 11:18
 * @Desc：
 **/
public interface EmployeeRepository extends CrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

}
