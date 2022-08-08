package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;
import com.example.mapper.EnterpriseMapper;
import com.example.service.EnterpriseService;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    
    /**
     * plus
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    @Override
    public IPage<Enterprise> searchPlus(EnterpriseSearchDTO enterpriseSearchDTO) {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(enterpriseSearchDTO.getName())) {
            wrapper.like("name", enterpriseSearchDTO.getName());
        }
        if (ObjectUtil.isNotNull(enterpriseSearchDTO.getStartDate()) && ObjectUtil.isNotNull(enterpriseSearchDTO.getEndDate())) {
            enterpriseSearchDTO.setEndDate(DateUtil.offsetDay(enterpriseSearchDTO.getEndDate(), 1));
            wrapper.between("create_time", enterpriseSearchDTO.getStartDate(), enterpriseSearchDTO.getEndDate());
        }
        return null;
    }
    
    /**
     * 验证电话是否被使用
     *
     * @param id
     * @param phone
     *
     * @return
     */
    @Override
    public int checkPhoneExists(Long id, String phone) {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        
        if (Objects.nonNull(id)) {
            wrapper.ne("id", id);
        }
        return super.baseMapper.selectCount(wrapper).intValue();
    }
}
