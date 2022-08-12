package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CarModelInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【car_model_info(车型信息表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.CarModelInfo
 */
@Mapper
public interface CarModelInfoMapper extends BaseMapper<CarModelInfo> {
    /**
     * 分页查询
     *
     * @param page
     * @param carTrainInfoSearchDTO
     *
     * @return
     */
    // IPage<CarModelInfo> search(IPage<CarModelInfo> iPage, CarTrainInfoSearchDTO carTrainInfoSearchDTO);
}
