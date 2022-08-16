package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.validtor.AddValidator;
import com.example.common.validtor.EditValidator;
import com.example.common.vo.PageVo;
import com.example.dto.EnterpriseSearchDTO;
import com.example.dto.PhoneValidatorDTO;
import com.example.entity.Enterprise;
import com.example.repository.EnterpriseRepository;
import com.example.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author： 17602
 * @Date： 2022/8/2 11:58
 * @Desc： 商家
 **/
@Api(tags = "商家信息")
@RestController
@CrossOrigin
@RequestMapping("/api/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseRepository repository;
    
    @Autowired
    private EnterpriseService service;
    
    /**
     * 分页查询
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    @ApiOperation("商家分页查询")
    @GetMapping("/data")
    public R search(EnterpriseSearchDTO enterpriseSearchDTO) {
        Page<Enterprise> search = service.search(enterpriseSearchDTO);
        PageVo<Enterprise> enterprisePageVo = PageVo.pageVo(search);
        return R.okHasData(enterprisePageVo);
    }
    
    
    @ApiOperation("检查电话是否存在")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "id为null代表新增", required = false),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true)
    })
    @GetMapping("/exists")
    public R findEmployeeById(@Valid PhoneValidatorDTO phoneValidatorDTO) {
        // 检查账号是否存在
        Long count = service.countByPhoneAndIdNot(phoneValidatorDTO.getPhone(), phoneValidatorDTO.getId());
        
        return R.ok();
    }
    
    @PostMapping("/add")
    public R addEnterprise(@RequestBody @Validated(value = {AddValidator.class}) Enterprise enterprise) {
        Optional<Enterprise> optional = repository.findById(enterprise.getId());
        Enterprise hasEnterprise = optional.get();
        Enterprise save = null;
        if (ObjectUtil.isNull(hasEnterprise)) {
            // 取出密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passEncoded = encoder.encode(enterprise.getLogin_pwd());
            // 存入加密后的密码
            enterprise.setLogin_pwd(passEncoded);
            save = repository.save(enterprise);
        }
        return ObjectUtil.isNotNull(save) ? R.ok() : R.build(AckCode.FAIL);
        
    }
    
    @ApiOperation("通过审核")
    @ApiImplicitParam(name = "id", value = "商家id", example = "1")
    @PostMapping("/approved")
    public R changeStatus(@RequestBody Map<String, Long> map) {
        Long id = map.get("id");
        Optional<Enterprise> optional = repository.findById(id);
        Enterprise enterprise = optional.get();
        if (ObjectUtil.isNotNull(enterprise)) {
            return R.build(AckCode.USER_NOT_FOUND);
        }
        enterprise.setStatus(1);
        repository.save(enterprise);
        return R.ok();
    }
    
    @ApiOperation("查询主键获取商家数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findEnterpriseById(@PathVariable(value = "id") Long id) {
        Optional<Enterprise> optional = repository.findById(id);
        Enterprise enterprise = optional.get();
        if (ObjectUtil.isNull(enterprise)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        enterprise.setCreate_time(null);
        enterprise.setUpdate_time(null);
        return R.okHasData(enterprise);
    }
    
    /**
     * 修改信息
     *
     * @param enterprise
     *
     * @return
     */
    @ApiOperation("编辑商家信息")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(value = {EditValidator.class}) Enterprise enterprise) {
        Enterprise update = null;
        // 查看是否有这个人
        Optional<Enterprise> optional = repository.findById(enterprise.getId());
        Enterprise hasEnterprise = optional.get();
        if (ObjectUtil.isNotNull(hasEnterprise)) {
            update = repository.save(enterprise);
        }
        return ObjectUtil.isNotNull(update) ? R.ok() : R.build(AckCode.FAIL);
    }
    
    /**
     * 删除
     *
     * @param ids
     *
     * @return
     */
    @ApiOperation("移除商家信息")
    @PostMapping("/del")
    public R delete(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            repository.deleteById(id);
        }
        return R.ok();
    }
}
