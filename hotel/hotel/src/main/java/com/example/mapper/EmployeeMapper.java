package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17602
 * @description 针对表【employee】的数据库操作Mapper
 * @createDate 2022-08-19 10:42:22
 * @Entity com.example.entity.Employee
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    IPage<Employee> search(IPage<Employee> page, @Param("emp") EmployeeSearchDTO employeeSearchDTO);
    
}
