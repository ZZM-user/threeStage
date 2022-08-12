package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CarModelAttrInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【car_model_attr_info(车型属性表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.CarModelAttrInfo
 */
@Mapper
public interface CarModelAttrInfoMapper extends BaseMapper<CarModelAttrInfo> {

}
