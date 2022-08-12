package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.CarModelInfo;

/**
 * @author 17602
 * @description 针对表【car_model_info(车型信息表)】的数据库操作Service
 * @createDate 2022-08-11 19:54:29
 */
public interface CarModelInfoService extends IService<CarModelInfo> {
    /**
     * 分页查询
     *
     * @param carTrainInfoSearchDTO
     *
     * @return
     */
    // IPage<CarModelInfo> search(CarTrainInfoSearchDTO carTrainInfoSearchDTO);
}
