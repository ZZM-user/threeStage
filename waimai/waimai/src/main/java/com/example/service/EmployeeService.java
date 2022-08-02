package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;

/**
 * @author 17602
 * @description 针对表【employee(后台管理员表)】的数据库操作Service
 * @createDate 2022-08-01 14:06:02
 */
public interface EmployeeService extends IService<Employee> {
    
    /**
     * 分页查询
     *
     * @param page
     * @param employeeSearchDTO
     *
     * @return
     */
    IPage<Employee> search(EmployeeSearchDTO employeeSearchDTO);
}
