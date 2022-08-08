package com.example.mapper;

import com.example.entity.CarModelInfo;

import java.util.List;

/**
 * @author 17602
 * @description 针对表【car_model_info(车型信息表)】的数据库操作Mapper
 * @createDate 2022-08-08 13:15:13
 * @Entity org.example.entity.CarModelInfo
 */
public interface CarModelInfoMapper {
    List<CarModelInfo> selectall();
}
