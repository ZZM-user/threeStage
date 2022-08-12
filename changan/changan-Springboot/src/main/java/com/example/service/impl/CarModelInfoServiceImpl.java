package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CarModelInfo;
import com.example.mapper.CarModelInfoMapper;
import com.example.service.CarModelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【car_model_info(车型信息表)】的数据库操作Service实现
 * @createDate 2022-08-11 19:54:29
 */
@Service
public class CarModelInfoServiceImpl extends ServiceImpl<CarModelInfoMapper, CarModelInfo>
        implements CarModelInfoService {
    @Autowired
    CarModelInfoMapper mapper;
    
    /**
     * 分页查询
     *
     * @param carTrainInfoSearchDTO
     *
     * @return
     */
    // @Override
    // public IPage<CarModelInfo> search(CarTrainInfoSearchDTO carTrainInfoSearchDTO) {
    //     Page<CarModelInfo> page = new Page<>(carTrainInfoSearchDTO.getPage(), carTrainInfoSearchDTO.getSize());
    //     return mapper.search(page, carTrainInfoSearchDTO);
    // }
}
