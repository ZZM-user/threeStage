package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import com.example.service.CategoryClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
        IPage<CategoryClass> categoryClassIPage = service.search(categoryClassSearchDTO);
        PageVo<CategoryClass> categoryClassPageVo = PageVo.pageVo(categoryClassIPage);
        return R.okHasData(categoryClassPageVo);
    }
    
    @ApiOperation("插入分类信息")
    @PostMapping("/add")
    public R insert(@RequestBody CategoryClass categoryClass) {
        // 判断是否重复
        List<CategoryClass> categoryClassList = service.hasRepeat(categoryClass);
        if (categoryClassList.size() != 0) {
            return R.build(AckCode.FAIL);
        }
        // 不重复就新增
        Date date = new Date();
        categoryClass.setCreate_time(date);
        categoryClass.setCreate_by("admin");
        boolean save = service.save(categoryClass);
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
}
