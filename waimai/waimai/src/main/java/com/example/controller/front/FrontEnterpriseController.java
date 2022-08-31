package com.example.controller.front;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.validtor.AddValidator;
import com.example.entity.Enterprise;
import com.example.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author： 17602
 * @Date： 2022/8/30 14:17
 * @Desc： 前端商家
 **/
@Api(tags = "前端商家")
@RequestMapping("/front")
@RestController
public class FrontEnterpriseController {
    
    @Autowired
    private EnterpriseService service;
    
    @ApiOperation("校验商家电话号是否已经注册")
    @GetMapping("/account/exists/{phone}")
    public R exitsPhtone(@PathVariable("phone") String phone) {
        if (StrUtil.isBlank(phone)) {
            return R.build(AckCode.PHONE_NUMBER_NOTNULL);
        }
    
        if (!PhoneUtil.isMobile(phone)) {
            return R.build(AckCode.PHONE_NUMBER_INVALID);
        }
        int i = this.service.checkPhoneExists(null, phone);
        return i == 0 ? R.ok() : R.build(AckCode.PHONE_NUMBER_SET_ACCOUNT_INVALID);
    }
    
    @ApiOperation("商家注册保存")
    @PostMapping("/enterprise/add")
    public R addEnterprise(@RequestBody @Validated(value = AddValidator.class) Enterprise enterprise) {
        boolean save = false;
        if (ObjectUtil.isNotNull(enterprise)) {
            // 取出密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passEncoded = encoder.encode(enterprise.getLogin_pwd());
            enterprise.setLogin_pwd(passEncoded);
            enterprise.setStatus(2);
            // 存入加密后的密码
            save = service.save(enterprise);
        }
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
}
