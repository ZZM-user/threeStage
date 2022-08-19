package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.vo.RecordVo;
import com.example.dto.RecordSearchDTO;
import com.example.entity.Record;
import com.example.mapper.RecordMapper;
import com.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【record】的数据库操作Service实现
 * @createDate 2022-08-19 10:42:22
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
        implements RecordService {
    @Autowired
    RecordMapper mapper;
    
    @Override
    public IPage<RecordVo> search(RecordSearchDTO recordSearchDTO) {
        IPage<RecordVo> page = new Page<RecordVo>(recordSearchDTO.getPage(), recordSearchDTO.getSize());
        return mapper.search(page, recordSearchDTO);
    }
}
