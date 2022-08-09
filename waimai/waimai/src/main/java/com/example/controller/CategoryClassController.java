package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import com.example.service.CategoryClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        boolean save = service.save(categoryClass);
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    
    @ApiOperation("查询主键获取分类数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findCategoryById(@PathVariable(value = "id") Long id) {
        CategoryClass category = service.getById(id);
        if (Objects.isNull(category)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        category.setCreate_time(null);
        category.setUpdate_time(null);
        return R.okHasData(category);
    }
    
    /**
     * 修改信息
     *
     * @param category
     *
     * @return
     */
    @ApiOperation("编辑分类信息")
    @PostMapping("/update")
    public R update(@RequestBody CategoryClass category) {
        boolean update = false;
        // 查看是否有这个分类
        CategoryClass hasCategoryClass = service.getById(category);
        if (ObjectUtil.isNotNull(hasCategoryClass)) {
            update = service.updateById(category);
        }
        return update ? R.ok() : R.build(AckCode.FAIL);
    }
    
    
    @ApiOperation("为商品菜单提供菜单查询")
    @GetMapping("/categorys")
    public R searchAllEnterprise(String categoryName) {
        LambdaQueryWrapper<CategoryClass> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(categoryName)) {
            queryWrapper.like(CategoryClass::getName, "%" + categoryName + "%");
        }
        queryWrapper.select(CategoryClass::getId, CategoryClass::getName);
        List<CategoryClass> categoryClassList = service.list(queryWrapper);
        return R.okHasData(categoryClassList);
    }
    
    /**
     * 删除
     *
     * @param ids
     *
     * @return
     */
    @ApiOperation("移除分类信息")
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
