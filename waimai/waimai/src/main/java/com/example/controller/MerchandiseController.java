package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import com.example.service.MerchandiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/8/2 14:01
 * @Desc： 商品信息
 **/
@Api(tags = "商品信息")
@RestController
@CrossOrigin
@RequestMapping("api/merchandise")
public class MerchandiseController {
    
    @Autowired
    MerchandiseService service;
    
    @ApiOperation("分页查询")
    @GetMapping("/data")
    public R search(MerchandiseSearchDTO merchandiseSearchDTO) {
        IPage<Merchandise> search = service.search(merchandiseSearchDTO);
        PageVo<Merchandise> merchandisePageVo = PageVo.pageVo(search);
        return R.okHasData(merchandisePageVo);
    }
}
