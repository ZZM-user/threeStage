package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.dto.CategoryClassSearchDTO;
import com.example.entity.CategoryClass;
import com.example.repository.CategoryClassRepository;
import com.example.service.CategoryClassService;
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
 * @Date： 2022/8/3 14:16
 * @Desc：
 **/
@Service
public class CategoryClassServiceImpl implements CategoryClassService {
    @Autowired
    CategoryClassRepository repository;
    
    @Override
    public Page<CategoryClass> search(CategoryClassSearchDTO categoryClassSearchDTO) {
        Pageable pageable = PageRequest.of(categoryClassSearchDTO.getPage() - 1, categoryClassSearchDTO.getSize());
        Specification<CategoryClass> specification = new Specification<CategoryClass>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Expression> list = new ArrayList<>();
                if (ObjectUtil.isNotNull(categoryClassSearchDTO.getEid()) && ObjectUtil.equal(1, categoryClassSearchDTO.getAccountType())) {
                    list.add(criteriaBuilder.like(root.get("enterprise_id"), "%" + categoryClassSearchDTO.getEid() + "%"));
                }
                if (ObjectUtil.isNotNull(categoryClassSearchDTO.getName())) {
                    list.add(criteriaBuilder.like(root.get("name"), "%" + categoryClassSearchDTO.getName() + "%"));
                }
                
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
                return query.where(predicates).getRestriction();
            }
        };
        
        return this.repository.findAll(specification, pageable);
    }
}
