package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.ContamerSearchDTO;
import com.example.entity.Contamer;
import com.example.service.ContamerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
