package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.dto.EnterpriseSearchDTO;
import com.example.dto.PhoneValidatorDTO;
import com.example.entity.Enterprise;
import com.example.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        IPage<Enterprise> search = service.search(enterpriseSearchDTO);
        PageVo<Enterprise> enterprisePageVo = PageVo.pageVo(search);
        return R.okHasData(enterprisePageVo);
    }
    
    
    @ApiOperation("检查电话是否存在")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "id为null代表新增", required = false),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true)
    })
    @GetMapping("/exists")
    public R findEmployeeById(@Valid PhoneValidatorDTO phoneValidatorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            R r = R.build(AckCode.SUCCESS);
            r.setMessage(message);
            return r;
        }
        // 检查账号是否存在
        int count = service.checkPhoneExists(phoneValidatorDTO.getId(), phoneValidatorDTO.getPhone());
        
        return count == 0 ? R.ok() : R.build(AckCode.VALUE_IS_USED);
    }
    
    @PostMapping("/add")
    public R addEnterprise(@RequestBody Enterprise enterprise) {
        Enterprise hasEnterprise = hasEnterprise(enterprise);
        boolean save = false;
        if (ObjectUtil.isNull(hasEnterprise)) {
            // 取出密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passEncoded = encoder.encode(enterprise.getLogin_pwd());
            // 存入加密后的密码
            enterprise.setLogin_pwd(passEncoded);
            // 构建创建时间、创建人
            enterprise.setCreate_time(new Date());
            enterprise.setCreate_by("admin");
            save = service.save(enterprise);
        }
        return save ? R.ok() : R.build(AckCode.FAIL);
        
    }
    
    @ApiOperation("通过审核")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @PostMapping("/approved")
    public R changeStatus(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        Enterprise enterprise = service.getById(id);
        if (ObjectUtil.isNull(enterprise)) {
            return R.build(AckCode.USER_NOT_FOUND);
        }
        enterprise.setStatus(1);
        boolean count = service.updateById(enterprise);
        return R.ok();
    }
    
    @ApiOperation("查询主键获取商家数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findEnterpriseById(@PathVariable(value = "id") Long id) {
        Enterprise enterprise = service.getById(id);
        if (Objects.isNull(enterprise)) {
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
    public R update(@RequestBody Enterprise enterprise) {
        boolean update = false;
        // 查看是否有这个人
        Enterprise hasEnterprise = hasEnterprise(enterprise);
        if (ObjectUtil.isNotNull(enterprise)) {
            // 构建更新时间、更新人
            enterprise.setUpdate_time(new Date());
            enterprise.setUpdate_by("admin");
            update = service.updateById(enterprise);
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
    @ApiOperation("移除商家信息")
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
    
    private Enterprise hasEnterprise(Enterprise enterprise) {
        LambdaQueryWrapper<Enterprise> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Enterprise> eq = wrapper.eq(Enterprise::getPhone, enterprise.getPhone());
        LambdaQueryWrapper<Enterprise> last = eq.last("limit 1");
        return service.getOne(last);
    }
}
