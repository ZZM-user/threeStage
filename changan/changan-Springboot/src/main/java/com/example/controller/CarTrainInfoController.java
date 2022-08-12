package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.CarTrainInfoSearchDTO;
import com.example.entity.CarTrainInfo;
import com.example.service.impl.CarTrainInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/8/11 20:16
 * @Desc：汽车模型管理
 **/
@Api("汽车模型管理")
@RestController
@CrossOrigin
@RequestMapping("/api/carModelManage")
public class CarTrainInfoController {
    @Autowired
    CarTrainInfoServiceImpl service;
    
    
    @ApiOperation("分页获取信息")
    @GetMapping("/data")
    public R data(CarTrainInfoSearchDTO carTrainInfoSearchDTO) {
        IPage<CarTrainInfo> search = service.search(carTrainInfoSearchDTO);
        PageVo<CarTrainInfo> pageVo = PageVo.pageVo(search);
        return R.okHasData(pageVo);
    }
}
