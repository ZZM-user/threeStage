package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;
import com.example.mapper.MerchandiseMapper;
import com.example.service.MerchandiseService;
import org.springframework.stereotype.Service;

/**
 * @author 17602
 * @description 针对表【merchandise(商品信息)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise>
        implements MerchandiseService {
    /**
     * 分页查询
     *
     * @param merchandiseSearchDTO
     *
     * @return
     */
    @Override
    public IPage<Merchandise> search(MerchandiseSearchDTO merchandiseSearchDTO) {
        Page<Merchandise> merchandisePage = new Page<>(merchandiseSearchDTO.getPage(), merchandiseSearchDTO.getSize());
        return super.baseMapper.search(merchandisePage, merchandiseSearchDTO);
    }
}
