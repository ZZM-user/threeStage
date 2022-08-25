package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.annon.AdminAccess;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.LoginUserVO;
import com.example.common.vo.PageVo;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import com.example.service.MerchandiseService;
import com.example.util.ThreadLocalUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    
    @AdminAccess
    @ApiOperation("分页查询-管理员版")
    @GetMapping("/data")
    public R search(MerchandiseSearchDTO merchandiseSearchDTO) {
        IPage<Merchandise> search = service.search(merchandiseSearchDTO);
        PageVo<Merchandise> merchandiseOfEnpVoPageVo = PageVo.pageVo(search);
        return R.okHasData(merchandiseOfEnpVoPageVo);
    }
    
    @ApiOperation("分页查询-商家版")
    @GetMapping("/byself")
    public R byself(MerchandiseSearchDTO merchandiseSearchDTO) {
        LoginUserVO loginUserVO = ThreadLocalUser.loginThreadLocal.get();
        merchandiseSearchDTO.setEnterpriseId(loginUserVO.getId());
        merchandiseSearchDTO.setEnterpriseName(null);
        IPage<Merchandise> search = service.search(merchandiseSearchDTO);
        PageVo<Merchandise> merchandiseOfEnpVoPageVo = PageVo.pageVo(search);
        return R.okHasData(merchandiseOfEnpVoPageVo);
    }
    
    @ApiOperation("插入商品信息")
    @PostMapping("/add")
    public R insert(@RequestBody Merchandise merchandise) {
        // 判断是否重复
        Merchandise hasMerchandise = service.getById(merchandise);
        if (ObjectUtil.isNotNull(hasMerchandise)) {
            return R.build(AckCode.FAIL);
        }
        boolean save = service.save(merchandise);
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    
    @ApiOperation("查询主键获取商品数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findCategoryById(@PathVariable(value = "id") Long id) {
        Merchandise merchandise = service.getById(id);
        if (Objects.isNull(merchandise)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        merchandise.setCreate_time(null);
        merchandise.setUpdate_time(null);
        return R.okHasData(merchandise);
    }
    
    @ApiOperation("")
    @PostMapping("/changeState")
    public R changeStatus(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        Merchandise merchandise = service.getById(id);
        if (ObjectUtil.isNull(merchandise)) {
            return R.build(AckCode.USER_NOT_FOUND);
        }
        int newState = merchandise.getIsgrounding() == 0 ? 1 : 0;
        merchandise.setIsgrounding(newState);
        boolean count = service.updateById(merchandise);
        return R.ok();
    }
    
    /**
     * 修改信息
     *
     * @param merchandise
     *
     * @return
     */
    @ApiOperation("编辑商品信息")
    @PostMapping("/update")
    public R update(@RequestBody Merchandise merchandise) {
        boolean update = false;
        // 查看是否有这个商品
        Merchandise hasMerchandise = service.getById(merchandise);
        if (ObjectUtil.isNotNull(hasMerchandise)) {
            update = service.updateById(merchandise);
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
    @ApiOperation("移除分商品信息")
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
