package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【employee(后台管理员表)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:02
* @Entity com.example.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
