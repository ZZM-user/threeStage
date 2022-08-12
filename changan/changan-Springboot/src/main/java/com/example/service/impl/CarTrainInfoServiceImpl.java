package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.CarTrainInfoSearchDTO;
import com.example.entity.CarTrainInfo;
import com.example.mapper.CarTrainInfoMapper;
import com.example.service.CarTrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【car_train_info(车系信息表)】的数据库操作Service实现
 * @createDate 2022-08-11 19:54:29
 */
@Service
public class CarTrainInfoServiceImpl extends ServiceImpl<CarTrainInfoMapper, CarTrainInfo>
        implements CarTrainInfoService {
    
    @Autowired
    CarTrainInfoMapper mapper;
    
    /**
     * 分页查询
     *
     * @param carTrainInfoSearchDTO
     *
     * @return
     */
    @Override
    public IPage<CarTrainInfo> search(CarTrainInfoSearchDTO carTrainInfoSearchDTO) {
        IPage<CarTrainInfo> page = new Page<>(carTrainInfoSearchDTO.getPage(), carTrainInfoSearchDTO.getSize());
        return mapper.search(page, carTrainInfoSearchDTO);
    }
}
