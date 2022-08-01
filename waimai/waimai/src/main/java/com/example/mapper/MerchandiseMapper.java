package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Merchandise;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【merchandise(商品信息)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:02
* @Entity com.example.entity.Merchandise
*/
@Mapper
public interface MerchandiseMapper extends BaseMapper<Merchandise> {

}
