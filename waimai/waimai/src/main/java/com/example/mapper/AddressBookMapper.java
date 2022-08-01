package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【address_book(地址管理)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:01
* @Entity com.example.entity.AddressBook
*/
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
