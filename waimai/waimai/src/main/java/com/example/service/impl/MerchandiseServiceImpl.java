package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Merchandise;
import com.example.service.MerchandiseService;
import com.example.mapper.MerchandiseMapper;
import org.springframework.stereotype.Service;

/**
* @author 17602
* @description 针对表【merchandise(商品信息)】的数据库操作Service实现
* @createDate 2022-08-01 14:06:02
*/
@Service
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise>
    implements MerchandiseService{

}




