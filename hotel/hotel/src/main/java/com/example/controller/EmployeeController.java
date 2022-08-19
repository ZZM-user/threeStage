package com.example.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/8/19 10:44
 * @Desc： 员工信息
 **/
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    
    @GetMapping("/data")
    public R search(EmployeeSearchDTO employeeSearchDTO) {
        IPage<Employee> search = service.search(employeeSearchDTO);
        PageVo<Employee> employeePageVo = PageVo.pageVo(search);
        return R.okHasData(employeePageVo);
    }
}
