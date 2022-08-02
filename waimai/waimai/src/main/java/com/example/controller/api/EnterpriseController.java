package com.example.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;
import com.example.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
