package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CarTrainAttrValueInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【car_train_attr_value_info(车系属性值表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.CarTrainAttrValueInfo
 */
@Mapper
public interface CarTrainAttrValueInfoMapper extends BaseMapper<CarTrainAttrValueInfo> {

}
