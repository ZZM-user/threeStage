package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;

/**
 * @author 17602
 * @description 针对表【enterprise(商家信息表)】的数据库操作Service
 * @createDate 2022-08-01 14:06:02
 */
public interface EnterpriseService extends IService<Enterprise> {
    /**
     * 分页查询
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    IPage<Enterprise> search(EnterpriseSearchDTO enterpriseSearchDTO);
    
    /**
     * plus
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    IPage<Enterprise> searchPlus(EnterpriseSearchDTO enterpriseSearchDTO);
    
}
