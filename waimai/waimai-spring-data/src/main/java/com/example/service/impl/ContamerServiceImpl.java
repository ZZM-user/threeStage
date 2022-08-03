package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.dto.ContamerSearchDTO;
import com.example.entity.Contamer;
import com.example.repository.ContamerRepository;
import com.example.service.ContamerService;
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
 * @Date： 2022/8/3 14:45
 * @Desc：
 **/
@Service
public class ContamerServiceImpl implements ContamerService {
    @Autowired
    ContamerRepository repository;
    
    @Override
    public Page search(ContamerSearchDTO contamerSearchDTO) {
        Pageable pageable = PageRequest.of(contamerSearchDTO.getPage() - 1, contamerSearchDTO.getSize());
        Specification<Contamer> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Expression> list = new ArrayList<>();
                if (StrUtil.isNotBlank(contamerSearchDTO.getLoginId())) {
                    list.add(criteriaBuilder.like(root.get("loginId"), "%" + contamerSearchDTO.getLoginId() + "%"));
                }
                if (ObjectUtil.isNotNull(contamerSearchDTO.getStatus())) {
                    list.add(criteriaBuilder.equal(root.get("status"), contamerSearchDTO.getStatus()));
                }
                if (ObjectUtil.isNotNull(contamerSearchDTO.getStartDate()) && ObjectUtil.isNotNull(contamerSearchDTO.getEndDate())) {
                    list.add(criteriaBuilder.between(root.get("create_time"), contamerSearchDTO.getStartDate(), DateUtil.offsetDay(contamerSearchDTO.getEndDate()
                            , 1)));
                }
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
                return query.where(predicates).getRestriction();
            }
        };
        return this.repository.findAll(specification, pageable);
    }
}
