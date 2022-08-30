package com.example.controller.front;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private EnterpriseService enterpriseService;
    
    @ApiOperation("校验商家电话号是否已经注册")
    @GetMapping("/account/exists/{phone}")
    public R exitsPhtone(@PathVariable("phone") String phone) {
        if (StrUtil.isBlank(phone)) {
            return R.build(AckCode.PHONE_NUMBER_NOTNULL);
        }
        
        if (!PhoneUtil.isMobile(phone)) {
            return R.build(AckCode.PHONE_NUMBER_INVALID);
        }
        int i = this.enterpriseService.checkPhoneExists(null, phone);
        return i == 0 ? R.ok() : R.build(AckCode.PHONE_NUMBER_SET_ACCOUNT_INVALID);
    }
}
