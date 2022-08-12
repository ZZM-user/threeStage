package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CarTrainAttrInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【car_train_attr_info(车系属性信息表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.CarTrainAttrInfo
 */
@Mapper
public interface CarTrainAttrInfoMapper extends BaseMapper<CarTrainAttrInfo> {

}
