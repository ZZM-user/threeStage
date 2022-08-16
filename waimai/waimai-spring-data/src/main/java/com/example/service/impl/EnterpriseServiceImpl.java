package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.dto.EnterpriseSearchDTO;
import com.example.entity.Enterprise;
import com.example.repository.EnterpriseRepository;
import com.example.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/3 11:26
 * @Desc：
 **/
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    
    @Autowired
    EnterpriseRepository enterpriseRepository;
    
    @Override
    public Page<Enterprise> search(EnterpriseSearchDTO enterpriseSearchDTO) {
        // 分页从0开始
        Pageable pageable = PageRequest.of(enterpriseSearchDTO.getPage() - 1, enterpriseSearchDTO.getSize());
        
        // 动态sql
        Specification<Enterprise> specification = new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Expression> list = new ArrayList<>();
                if (StrUtil.isNotBlank(enterpriseSearchDTO.getName())) {
                    list.add(criteriaBuilder.like(root.get("name"), "%" + enterpriseSearchDTO.getName() + "%"));
                }
                if (StrUtil.isNotBlank(enterpriseSearchDTO.getPhone())) {
                    list.add(criteriaBuilder.like(root.get("phone"), "%" + enterpriseSearchDTO.getPhone() + "%"));
                }
                if (ObjectUtil.isNotNull(enterpriseSearchDTO.getStatus())) {
                    list.add(criteriaBuilder.equal(root.get("status"), enterpriseSearchDTO.getStatus()));
                }
                if (ObjectUtil.isNotNull(enterpriseSearchDTO.getStartDate()) && ObjectUtil.isNotNull(enterpriseSearchDTO.getEndDate())) {
                    list.add(criteriaBuilder.between(root.get("create_time"), enterpriseSearchDTO.getStartDate(), DateUtil.offsetDay(enterpriseSearchDTO.getEndDate(), 1)));
                }
    
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
    
                return query.where(predicates).getRestriction();
            }
        };
    
        // 返回结果
        return this.enterpriseRepository.findAll(specification, pageable);
    }
    
    @Override
    public Long countByPhoneAndIdNot(String phone, Long id) {
        return null;
    }
    
    @Override
    public Long countByPhone(String phone) {
        return null;
    }
}
