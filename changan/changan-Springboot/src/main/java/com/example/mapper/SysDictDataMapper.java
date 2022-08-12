package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【sys_dict_data(字典数据表)】的数据库操作Mapper
 * @createDate 2022-08-11 19:54:29
 * @Entity com.example.entity.SysDictData
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

}
