package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.annon.AdminAccess;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.dto.ContamerSearchDTO;
import com.example.dto.LoginIdValidatorDTO;
import com.example.entity.Contamer;
import com.example.service.ContamerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @Author： 17602
 * @Date： 2022/8/2 13:12
 * @Desc： 会员
 **/
@Api(tags = "会员接口")
@RestController
@CrossOrigin
@RequestMapping("/api/contamer")
public class ContamerController {
    
    @Autowired
    ContamerService service;
    
    @ApiOperation("分页查询")
    @GetMapping("/data")
    public R search(ContamerSearchDTO contamerSearchDTO) {
        IPage<Contamer> search = service.search(contamerSearchDTO);
        PageVo<Contamer> contamerPageVo = PageVo.pageVo(search);
        return R.okHasData(contamerPageVo);
    }
    
    @ApiOperation("检查电话是否存在")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "id为null代表新增", required = false),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true)
    })
    @GetMapping("/exists")
    public R findEmployeeById(@Valid LoginIdValidatorDTO loginIdValidatorDTO) {
        // 检查账号是否存在
        int count = service.checkLoginIdExists(loginIdValidatorDTO.getId(), loginIdValidatorDTO.getLoginId());
        
        return count == 0 ? R.ok() : R.build(AckCode.VALUE_IS_USED);
    }
    
    @AdminAccess
    @ApiOperation("增加会员信息")
    @PostMapping("/add")
    public R addContamer(@RequestBody @Valid Contamer contamer) {
        Contamer hasContamer = service.getById(contamer);
        boolean save = false;
        if (ObjectUtil.isNull(hasContamer)) {
            // 取出密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passEncoded = encoder.encode(contamer.getPasswold());
            // 存入加密后的密码
            contamer.setPasswold(passEncoded);
            save = service.save(contamer);
        }
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    
    @ApiOperation("查询主键获取会员数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findContamerById(@PathVariable(value = "id") Long id) {
        Contamer contamer = service.getById(id);
        if (Objects.isNull(contamer)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        contamer.setCreate_time(null);
        contamer.setUpdate_time(null);
        return R.okHasData(contamer);
    }
    
    /**
     * 修改信息
     *
     * @param contamer
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("编辑商家信息")
    @PostMapping("/update")
    public R update(@RequestBody Contamer contamer) {
        boolean update = false;
        // 查看是否有这个人
        Contamer hasContamer = service.getById(contamer);
        if (ObjectUtil.isNotNull(contamer)) {
            update = service.updateById(contamer);
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
    @ApiOperation("移除会员信息")
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
    
}
