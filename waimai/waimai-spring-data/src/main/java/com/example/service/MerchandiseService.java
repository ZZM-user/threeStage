package com.example.service;

import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import org.springframework.data.domain.Page;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:31
 * @Desc：
 **/
public interface MerchandiseService {
    Page<Merchandise> search(MerchandiseSearchDTO merchandiseSearchDTO);
    
}
