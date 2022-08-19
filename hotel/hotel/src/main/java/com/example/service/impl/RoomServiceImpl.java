package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.vo.RoomVo;
import com.example.dto.RoomSearchDTO;
import com.example.entity.Room;
import com.example.mapper.RoomMapper;
import com.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【room】的数据库操作Service实现
 * @createDate 2022-08-19 10:42:22
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room>
        implements RoomService {
    @Autowired
    RoomMapper mapper;
    
    @Override
    public IPage<RoomVo> search(RoomSearchDTO roomSearchDTO) {
        Page<RoomVo> page = new Page<>(roomSearchDTO.getPage(), roomSearchDTO.getSize());
        return mapper.search(page, roomSearchDTO);
    }
}
