package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import com.example.repository.MerchandiseRepository;
import com.example.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;

/**
 * @Author： 17602
 * @Date： 2022/8/3 14:31
 * @Desc：
 **/
@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    
    @Autowired
    MerchandiseRepository repository;
    
    @Override
    public Page<Merchandise> search(MerchandiseSearchDTO merchandiseSearchDTO) {
        Pageable pageable = PageRequest.of(merchandiseSearchDTO.getPage() - 1, merchandiseSearchDTO.getSize());
        
        Specification<Merchandise> specialaction = new Specification<Merchandise>() {
            @Override
            public Predicate toPredicate(Root<Merchandise> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Expression> list = new ArrayList<>();
                if (ObjectUtil.isNotNull(merchandiseSearchDTO.getEid()) && ObjectUtil.equal(1, merchandiseSearchDTO.getAccountType())) {
                    list.add(criteriaBuilder.like(root.get("b_id"), "%" + merchandiseSearchDTO.getEid() + "%"));
                }
                if (StrUtil.isNotBlank(merchandiseSearchDTO.getName())) {
                    list.add(criteriaBuilder.like(root.get("name"), "%" + merchandiseSearchDTO.getName() + "%"));
                }
                if (ObjectUtil.isNotNull(merchandiseSearchDTO.getIsgrounding())) {
                    list.add(criteriaBuilder.equal(root.get("isgrouding"), merchandiseSearchDTO.getIsgrounding()));
                }
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
                return query.where(predicates).getRestriction();
            }
        };
        return this.repository.findAll(specialaction, pageable);
    }
}
