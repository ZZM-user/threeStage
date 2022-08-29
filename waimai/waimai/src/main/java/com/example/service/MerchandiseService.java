package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.vo.MerchandiseDataVO;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.Merchandise;

/**
 * @author 17602
 * @description 针对表【merchandise(商品信息)】的数据库操作Service
 * @createDate 2022-08-01 14:06:02
 */
public interface MerchandiseService extends IService<Merchandise> {
    /**
     * 分页查询
     *
     * @param merchandiseSearchDTO
     *
     * @return
     */
    IPage<Merchandise> search(MerchandiseSearchDTO merchandiseSearchDTO);
    
    MerchandiseDataVO findById(Long id);
}
