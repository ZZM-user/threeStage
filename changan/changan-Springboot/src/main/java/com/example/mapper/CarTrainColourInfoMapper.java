package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CarTrainColourInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【car_train_colour_info(车系颜色表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.CarTrainColourInfo
 */
@Mapper
public interface CarTrainColourInfoMapper extends BaseMapper<CarTrainColourInfo> {

}
