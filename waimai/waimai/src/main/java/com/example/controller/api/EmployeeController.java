package com.example.controller.api;

import com.example.common.vo.R;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/1 15:09
 * @Desc：后台管理员api接口
 **/
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService service;
    
    /**
     * 全查
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<Employee> list = service.list();
        return R.success(list);
    }
    
    /**
     * 添加信息
     *
     * @param employee
     *
     * @return
     */
    @PostMapping("/add")
    public R add(Employee employee) {
        boolean save = service.save(employee);
        return save ? R.success() : R.failed();
    }
    
    /**
     * 修改信息
     *
     * @param employee
     *
     * @return
     */
    @PostMapping("/update")
    public R update(Employee employee) {
        boolean b = service.updateById(employee);
        return b ? R.success() : R.failed();
    }
    
    /**
     * 根据名称搜索
     *
     * @param name
     *
     * @return
     */
    @GetMapping("/search")
    public R searchOne(@RequestParam String name) {
        Employee employee = service.selectOneByLoginName(name);
        return employee != null ? R.success(employee) : R.failed();
    }
}
