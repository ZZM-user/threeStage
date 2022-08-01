package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.AddressBook;
import com.example.service.AddressBookService;
import com.example.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author 17602
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-08-01 14:06:01
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




