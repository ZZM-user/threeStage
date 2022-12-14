package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import com.example.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【employee】的数据库操作Service实现
 * @createDate 2022-08-19 10:42:22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
    @Autowired
    EmployeeMapper mapper;
    
    @Override
    public IPage<Employee> search(EmployeeSearchDTO employeeSearchDTO) {
        Page<Employee> page = new Page<>(employeeSearchDTO.getPage(), employeeSearchDTO.getSize());
        return mapper.search(page, employeeSearchDTO);
    }
}
