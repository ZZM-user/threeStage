package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.vo.PageVo;
import com.example.common.vo.RecordVo;
import com.example.dto.RecordSearchDTO;
import com.example.entity.Record;
import com.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author： 17602
 * @Date： 2022/8/19 12:37
 * @Desc：
 **/
@RestController
@RequestMapping("/api/record")
public class RecordController {
    @Autowired
    RecordService service;
    
    @GetMapping("/data")
    public R search(RecordSearchDTO recordSearchDTO) {
        IPage<RecordVo> search = service.search(recordSearchDTO);
        PageVo<RecordVo> recordVoPageVo = PageVo.pageVo(search);
        return R.okHasData(recordVoPageVo);
    }
    
    @PostMapping("/enter")
    public R enter(@RequestBody Record record) {
        System.out.println("接收到的数据: " + record.toString());
        boolean save = service.save(record);
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
}
