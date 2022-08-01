package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Enterprise;
import com.example.service.EnterpriseService;
import com.example.mapper.EnterpriseMapper;
import org.springframework.stereotype.Service;

/**
* @author 17602
* @description 针对表【enterprise(商家信息表)】的数据库操作Service实现
* @createDate 2022-08-01 14:06:02
*/
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise>
    implements EnterpriseService{

}




