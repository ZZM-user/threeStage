package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.vo.LoginUserVO;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;
import com.example.mapper.EnterpriseMapper;
import com.example.service.EnterpriseService;
import com.example.util.ThreadLocalUser;
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
    
    @Override
    public IPage<Enterprise> searchByEnterprise() {
        Page<Enterprise> enterprisePage = new Page<>(1, 10);
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        LoginUserVO loginUserVO = ThreadLocalUser.loginThreadLocal.get();
        wrapper.eq("phone", loginUserVO.getAccount());
        return super.baseMapper.selectPage(enterprisePage, wrapper);
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
