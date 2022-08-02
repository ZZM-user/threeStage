package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;
import com.example.mapper.EnterpriseMapper;
import com.example.service.EnterpriseService;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【enterprise(商家信息表)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise>
        implements EnterpriseService {
    /**
     * 分页查询
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    @Override
    public IPage<Enterprise> search(EnterpriseSearchDTO enterpriseSearchDTO) {
        Page<Enterprise> enterprisePage = new Page<>(enterpriseSearchDTO.getPage(), enterpriseSearchDTO.getSize());
        return super.baseMapper.search(enterprisePage, enterpriseSearchDTO);
    }
}
