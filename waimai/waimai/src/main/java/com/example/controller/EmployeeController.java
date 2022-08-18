package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.annon.AdminAccess;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @AdminAccess
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
    @AdminAccess
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
            save = service.save(employee);
        }
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    @AdminAccess
    @ApiOperation("查询主键获取员工数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findEmployeeById(@PathVariable(value = "id") Long id) {
        Employee employee = service.getById(id);
        if (Objects.isNull(employee)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        employee.setCreate_time(null);
        employee.setUpdate_time(null);
        return R.okHasData(employee);
    }
    
    /**
     * 测试电话是否存在
     *
     * @param id
     * @param phone
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("检查登录名是否存在")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "id为null代表新增", required = false),
            @ApiImplicitParam(name = "login_name", value = "登录名", required = true)
    })
    @GetMapping("/exists")
    public R findEmployeeById(Long id, String loginName) {
        if (StrUtil.isBlank(loginName)) {
            return R.build(AckCode.USER_PARAM_IS_NOT_NULL);
        }
        // 检查账号是否存在
        int count = service.checkPhoneExists(id, loginName);
        
        return count == 0 ? R.ok() : R.build(AckCode.VALUE_IS_USED);
    }
    
    
    @AdminAccess
    @ApiOperation("切换员工账号状态")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @PostMapping("/changeStatus")
    public R changeStatus(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        Employee employee = service.getById(id);
        if (ObjectUtil.isNull(employee)) {
            return R.build(AckCode.USER_NOT_FOUND);
        }
        Integer status = employee.getStatus();
        Integer newStatus = status == 1 ? 0 : 1;
        employee.setStatus(newStatus);
        boolean count = service.updateById(employee);
        return R.ok();
    }
    
    /**
     * 修改信息
     *
     * @param employee
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("编辑员工信息")
    @PostMapping("/update")
    public R update(@RequestBody Employee employee) {
        boolean update = false;
        // 查看是否有这个人
        Employee hasEmployee = hasEmployee(employee);
        if (ObjectUtil.isNotNull(hasEmployee)) {
            update = service.updateById(employee);
        }
        return update ? R.ok() : R.build(AckCode.FAIL);
    }
    
    /**
     * 删除
     *
     * @param ids
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("移除员工信息")
    @PostMapping("/del")
    public R delete(@RequestBody List<Integer> ids) {
        int counter = 0;
        boolean remove;
        for (Integer id : ids) {
            remove = service.removeById(id);
            counter += remove ? 1 : 0;
        }
        return counter == ids.size() ? R.ok() : R.build(AckCode.FAIL);
    }
    
    private Employee hasEmployee(Employee employee) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Employee> eq = wrapper.eq(Employee::getLogin_name, employee.getLogin_name());
        LambdaQueryWrapper<Employee> last = eq.last("limit 1");
        return service.getOne(last);
    }
}
