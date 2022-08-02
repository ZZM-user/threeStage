package com.example.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author： 17602
 * @Date： 2022/8/1 15:09
 * @Desc：后台管理员api接口
 **/
@Api(tags = "员工接口")
@RestController
@CrossOrigin
@RequestMapping("/api/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService service;
    
    
    /**
     * 分页查询
     *
     * @param employeeSearchDTO
     *
     * @return
     */
    @ApiOperation("员工分页查询")
    @GetMapping("/data")
    public R search(EmployeeSearchDTO employeeSearchDTO) {
        IPage<Employee> search = service.search(employeeSearchDTO);
        PageVo<Employee> employeePageVo = PageVo.pageVo(search);
        return R.okHasData(employeePageVo);
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
        return save ? R.ok() : R.build(AckCode.FAIL);
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
        return b ? R.ok() : R.build(AckCode.FAIL);
    }
    
}
