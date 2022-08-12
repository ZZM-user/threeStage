package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.CarTrainInfoSearchDTO;
import com.example.entity.CarTrainInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17602
 * @description 针对表【car_train_info(车系信息表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.CarTrainInfo
 */
@Mapper
public interface CarTrainInfoMapper extends BaseMapper<CarTrainInfo> {
    
    /**
     * 分页查询
     *
     * @param iPage
     * @param carTrainInfoSearchDTO
     *
     * @return
     */
    IPage<CarTrainInfo> search(IPage<CarTrainInfo> iPage, @Param("cti") CarTrainInfoSearchDTO carTrainInfoSearchDTO);
}
