package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;

/**
 * @author 17602
 * @description 针对表【employee】的数据库操作Service
 * @createDate 2022-08-19 10:42:22
 */
public interface EmployeeService extends IService<Employee> {
    IPage<Employee> search(EmployeeSearchDTO employeeSearchDTO);
}
