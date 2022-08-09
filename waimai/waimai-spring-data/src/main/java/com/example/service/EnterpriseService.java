package com.example.service;

import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;
import org.springframework.data.domain.Page;

/**
 * @Author： 17602
 * @Date： 2022/8/3 11:24
 * @Desc：
 **/
public interface EnterpriseService {
    Page<Enterprise> search(EnterpriseSearchDTO enterpriseSearchDTO);
    
    Long countByPhoneAndIdNot(String phone, Long id);
    
    Long countByPhone(String phone);
}
