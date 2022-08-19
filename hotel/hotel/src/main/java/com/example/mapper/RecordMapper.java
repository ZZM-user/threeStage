package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.vo.RecordVo;
import com.example.dto.RecordSearchDTO;
import com.example.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17602
 * @description 针对表【record】的数据库操作Mapper
 * @createDate 2022-08-19 10:42:22
 * @Entity com.example.entity.Record
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    IPage<RecordVo> search(IPage<RecordVo> page, @Param("rdto") RecordSearchDTO recordSearchDTO);
}
