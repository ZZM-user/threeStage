package com.example.service;

import com.example.dto.ContamerSearchDTO;
import com.example.entity.Contamer;
import org.springframework.data.domain.Page;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:44
 * @Desc：
 **/
public interface ContamerService {
    Page<Contamer> search(ContamerSearchDTO contamerSearchDTO);
    
}
