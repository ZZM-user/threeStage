package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @ApiOperation("新增员工信息")
    @PostMapping("/add")
    public R add(@RequestBody Employee employee) {
        Employee hasEmployee = hasEmployee(employee);
        boolean save = false;
        if (ObjectUtil.isNull(hasEmployee)) {
            // 取出密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passEncoded = encoder.encode(employee.getLogin_pwd());
            // 存入加密后的密码
            employee.setLogin_pwd(passEncoded);
            // 构建创建时间、创建人
            employee.setCreate_time(new Date());
            employee.setCreate_by("admin");
            save = service.save(employee);
        }
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    /**
     * 修改信息
     *
     * @param employee
     *
     * @return
     */
    @ApiOperation("编辑员工信息")
    @PostMapping("/update")
    public R update(@RequestBody Employee employee) {
        boolean update = false;
        // 查看是否有这个人
        Employee hasEmployee = hasEmployee(employee);
        if (ObjectUtil.isNotNull(hasEmployee)) {
            // 构建更新时间、更新人
            employee.setUpdate_time(new Date());
            employee.setUpdate_by("admin");
            update = service.updateById(employee);
        }
        return update ? R.ok() : R.build(AckCode.FAIL);
    }
    
    /**
     * 删除
     *
     * @param employee
     *
     * @return
     */
    @ApiOperation("移除员工信息")
    @PostMapping("/del")
    public R delete(@RequestBody List<Employee> employeeList) {
        int counter = 0;
        boolean remove;
        for (Employee employee : employeeList) {
            if (ObjectUtil.isNotNull(employee.getId())) {
                remove = service.removeById(employee);
                counter += remove ? 1 : 0;
            }
        }
        return counter == employeeList.size() ? R.ok() : R.build(AckCode.FAIL);
    }
    
    private Employee hasEmployee(Employee employee) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Employee> eq = wrapper.eq(Employee::getLogin_name, employee.getLogin_name());
        LambdaQueryWrapper<Employee> last = eq.last("limit 1");
        return service.getOne(last);
    }
}
