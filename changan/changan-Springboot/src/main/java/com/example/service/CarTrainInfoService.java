package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.CarTrainInfoSearchDTO;
import com.example.entity.CarTrainInfo;

/**
 * @author 17602
 * @description 针对表【car_train_info(车系信息表)】的数据库操作Service
 * @createDate 2022-08-11 19:54:29
 */
public interface CarTrainInfoService extends IService<CarTrainInfo> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param carTrainInfoSearchDTO
     *
     * @return
     */
    IPage<CarTrainInfo> search(CarTrainInfoSearchDTO carTrainInfoSearchDTO);
}
