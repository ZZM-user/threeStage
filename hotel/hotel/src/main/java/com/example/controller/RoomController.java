package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.common.vo.RoomVo;
import com.example.dto.RoomSearchDTO;
import com.example.entity.Room;
import com.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author： 17602
 * @Date： 2022/8/19 11:33
 * @Desc：
 **/
@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    RoomService service;
    
    @GetMapping("/data")
    public R search(RoomSearchDTO roomSearchDTO) {
        IPage<RoomVo> search = service.search(roomSearchDTO);
        PageVo<RoomVo> roomVoPageVo = PageVo.pageVo(search);
        return R.okHasData(roomVoPageVo);
    }
    
    @PostMapping("/add")
    public R enter(@RequestBody Room room) {
        boolean save = service.save(room);
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
}
