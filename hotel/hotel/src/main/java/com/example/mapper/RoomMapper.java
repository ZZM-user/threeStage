package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.vo.RoomVo;
import com.example.dto.RoomSearchDTO;
import com.example.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17602
 * @description 针对表【room】的数据库操作Mapper
 * @createDate 2022-08-19 10:42:22
 * @Entity com.example.entity.Room
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    
    IPage<RoomVo> search(IPage<RoomVo> page, @Param("rdt") RoomSearchDTO roomSearchDTO);
}
