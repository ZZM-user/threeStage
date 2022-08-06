package com.example.controller;

import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import com.example.service.CategoryClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/8/2 13:33
 * @Desc： 菜单
 **/
@Api(tags = "菜单信息")
@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryClassController {
    
    @Autowired
    CategoryClassService service;
    
    @ApiOperation("分页查询")
    @GetMapping("/data")
    public R search(CategoryClassSearchDTO categoryClassSearchDTO) {
        Page<CategoryClass> categoryClassIPage = service.search(categoryClassSearchDTO);
        PageVo<CategoryClass> categoryClassPageVo = PageVo.pageVo(categoryClassIPage);
        return R.okHasData(categoryClassPageVo);
    }
    
}
