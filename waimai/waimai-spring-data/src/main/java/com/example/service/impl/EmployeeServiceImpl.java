package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.dto.EmployeeSearchDTO;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
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
 * @Date： 2022/8/3 14:55
 * @Desc：
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;
    
    @Override
    public Page search(EmployeeSearchDTO employeeSearchDTO) {
        Pageable pageable = PageRequest.of(employeeSearchDTO.getPage() - 1, employeeSearchDTO.getSize());
        Specification<Employee> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Expression> list = new ArrayList<>();
                if (StrUtil.isNotBlank(employeeSearchDTO.getLoginName())) {
                    list.add(criteriaBuilder.like(root.get("login_name"), "%" + employeeSearchDTO.getLoginName() + "%"));
                }
                if (ObjectUtil.isNotNull(employeeSearchDTO.getStatus())) {
                    list.add(criteriaBuilder.equal(root.get("status"), employeeSearchDTO.getStatus()));
                }
                
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
                return query.where(predicates).getRestriction();
            }
        };
        return this.repository.findAll(specification, pageable);
    }
}
