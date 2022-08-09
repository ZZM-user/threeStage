package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.ContamerSearchDTO;
import com.example.entity.Contamer;

/**
 * @author 17602
 * @description 针对表【contamer(前端用户)】的数据库操作Service
 * @createDate 2022-08-01 14:06:02
 */
public interface ContamerService extends IService<Contamer> {
    /**
     * 分页查询
     *
     * @param contamerSearchDTO
     *
     * @return
     */
    IPage<Contamer> search(ContamerSearchDTO contamerSearchDTO);
    
    int checkLoginIdExists(Long id, String loginId);
}
