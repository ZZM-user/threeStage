package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.ContamerSearchDTO;
import com.example.entity.Contamer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17602
 * @description 针对表【contamer(前端用户)】的数据库操作Mapper
 * @createDate 2022-08-01 14:06:02
 * @Entity com.example.entity.Contamer
 */
@Mapper
public interface ContamerMapper extends BaseMapper<Contamer> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param contamer
     *
     * @return
     */
    IPage<Contamer> search(IPage<?> iPage, @Param("con") ContamerSearchDTO contamerSearchDTO);
}
