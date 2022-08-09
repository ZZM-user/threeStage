package com.example.service;

import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import org.springframework.data.domain.Page;

/**
 * @Author： 17602
 * @Date： 2022/8/3 11:22
 * @Desc：
 **/
public interface EmployeeService {
    Page<Employee> search(EmployeeSearchDTO employeeSearchDTO);
    
    Employee getById(Long id);
}
