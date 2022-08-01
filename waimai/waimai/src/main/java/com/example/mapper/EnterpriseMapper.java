package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Enterprise;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【enterprise(商家信息表)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:02
* @Entity com.example.entity.Enterprise
*/
@Mapper
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

}
