package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.ContamerSearchDTO;
import com.example.entity.Contamer;
import com.example.mapper.ContamerMapper;
import com.example.service.ContamerService;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【contamer(前端用户)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class ContamerServiceImpl extends ServiceImpl<ContamerMapper, Contamer>
        implements ContamerService {
    /**
     * 分页查询
     *
     * @param contamerSearchDTO
     *
     * @return
     */
    @Override
    public IPage<Contamer> search(ContamerSearchDTO contamerSearchDTO) {
        Page<Contamer> contamerPage = new Page<>(contamerSearchDTO.getPage(), contamerSearchDTO.getSize());
        return super.baseMapper.search(contamerPage, contamerSearchDTO);
    }
}
