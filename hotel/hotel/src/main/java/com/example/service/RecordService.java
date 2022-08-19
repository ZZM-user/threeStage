package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.vo.RecordVo;
import com.example.dto.RecordSearchDTO;
import com.example.entity.Record;

/**
 * @author 17602
 * @description 针对表【record】的数据库操作Service
 * @createDate 2022-08-19 10:42:22
 */
public interface RecordService extends IService<Record> {
    IPage<RecordVo> search(RecordSearchDTO recordSearchDTO);
    
}
