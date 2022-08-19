package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.vo.RoomVo;
import com.example.dto.RoomSearchDTO;
import com.example.entity.Room;

/**
 * @author 17602
 * @description 针对表【room】的数据库操作Service
 * @createDate 2022-08-19 10:42:22
 */
public interface RoomService extends IService<Room> {
    IPage<RoomVo> search(RoomSearchDTO roomSearchDTO);
}
