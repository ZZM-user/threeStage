package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17602
 * @description 针对表【merchandise(商品信息)】的数据库操作Mapper
 * @createDate 2022-08-01 14:06:02
 * @Entity com.example.entity.Merchandise
 */
@Mapper
public interface MerchandiseMapper extends MPJBaseMapper<Merchandise> {
    
    /**
     * 分页查询
     *
     * @param page
     * @param merchandiseSearchDTO
     *
     * @return
     */
    IPage<Merchandise> search(IPage<?> page, @Param("mer") MerchandiseSearchDTO merchandiseSearchDTO);
    
}
