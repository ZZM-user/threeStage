package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.annon.AdminAccess;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.LoginUserVO;
import com.example.common.vo.MerchandiseDataVO;
import com.example.common.vo.PageVo;
import com.example.dto.DishFlavorsDTO;
import com.example.dto.MerchandiseDataDTO;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.DishFlavor;
import com.example.entity.Merchandise;
import com.example.service.DishFlavorService;
import com.example.service.MerchandiseService;
import com.example.util.ThreadLocalUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    
    @Autowired
    DishFlavorService dishFlavorService;
    
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
    @Transactional(rollbackFor = Exception.class)
    public R insert(@RequestBody MerchandiseDataDTO merchandise) {
        // 判断是否重复
        Merchandise hasMerchandise = service.getById(merchandise);
        if (ObjectUtil.isNotNull(hasMerchandise)) {
            return R.build(AckCode.FAIL);
        }
        boolean save = service.save(merchandise);
        Long id = merchandise.getId();
        List<DishFlavorsDTO> dishFlavors = merchandise.getDishFlavors();
        addDishFlavors(id, dishFlavors);
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    
    @ApiOperation("查询主键获取商品数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findMerchandiseById(@PathVariable(value = "id") Long id) {
        MerchandiseDataVO merchandiseDataVO = service.findById(id);
        if (Objects.isNull(merchandiseDataVO)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        merchandiseDataVO.setCreate_time(null);
        merchandiseDataVO.setUpdate_time(null);
        return R.okHasData(merchandiseDataVO);
    }
    
    @ApiOperation("修改商品上架状态")
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
        return count ? R.ok() : R.build(AckCode.FAIL);
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
    @Transactional(rollbackFor = Exception.class)
    public R update(@RequestBody MerchandiseDataDTO merchandiseDataDTO) {
        // 查看是否有这个商品
        Merchandise hasMerchandise = service.getById(merchandiseDataDTO);
        if (ObjectUtil.isNull(hasMerchandise)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        MerchandiseDataVO byId = service.findById(merchandiseDataDTO.getId());
        // 先删除后新增
        dishFlavorService.removeByDishId(byId.getId());
        addDishFlavors(byId.getId(), merchandiseDataDTO.getDishFlavors());
        
        boolean update = service.updateById(merchandiseDataDTO);
        return update ? R.ok() : R.build(AckCode.FAIL);
    }
    
    /**
     * 删除
     *
     * @param ids
     *
     * @return
     */
    @ApiOperation("移除商品信息")
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
    
    /**
     * 为指定商家添加口味信息
     *
     * @param mid
     * @param dishFlavors
     */
    private void addDishFlavors(Long mid, List<DishFlavorsDTO> dishFlavors) {
        if (ObjectUtil.isNotNull(mid) && ObjectUtil.isNotNull(dishFlavors) && dishFlavors.size() > 0) {
            ArrayList<DishFlavor> dishFlavorArrayList = new ArrayList<>();
            for (DishFlavorsDTO dishFlavor : dishFlavors) {
                if (dishFlavor.getTags().length != 0 || StrUtil.isNotBlank(dishFlavor.getName())) {
                    dishFlavorArrayList.add(new DishFlavor(mid, dishFlavor.getName(), JSONUtil.toJsonStr(dishFlavor.getTags()), 0));
                }
            }
            dishFlavorService.saveBatch(dishFlavorArrayList);
        }
    }
}
