package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;

import java.util.List;

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
     * 验证电话是否被使用
     *
     * @param id
     * @param phone
     *
     * @return
     */
    int checkPhoneExists(Long id, String phone);
    
    IPage<Enterprise> searchByEnterprise();
    
    List<Enterprise> export(EnterpriseSearchDTO enterpriseSearchDTO);
}
