package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.vo.MerchandiseOfEnpVo;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import com.example.mapper.MerchandiseMapper;
import com.example.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【merchandise(商品信息)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise>
        implements MerchandiseService {
    @Autowired
    MerchandiseMapper mapper;
    
    /**
     * 分页查询
     *
     * @param merchandiseSearchDTO
     *
     * @return
     */
    @Override
    public IPage<MerchandiseOfEnpVo> search(MerchandiseSearchDTO merchandiseSearchDTO) {
        Page<Merchandise> page = new Page<>(merchandiseSearchDTO.getPage(), merchandiseSearchDTO.getSize());
        return mapper.search(page, merchandiseSearchDTO);
    }
}
