package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Employee;
import com.example.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【employee(后台管理员表)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
    @Autowired
    EmployeeMapper mapper;
    
    @Override
    public Employee selectOneByLoginName(String loginName) {
        return mapper.selectOneByLoginName(loginName);
    }
}
