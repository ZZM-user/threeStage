package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.vo.MerchandiseDataVO;
import com.example.dto.DishFlavorsDTO;
import com.example.dto.MerchandiseSearchDTO;
import com.example.entity.DishFlavor;
import com.example.entity.Merchandise;
import com.example.mapper.MerchandiseMapper;
import com.example.service.DishFlavorService;
import com.example.service.MerchandiseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 17602
 * @description 针对表【merchandise(商品信息)】的数据库操作Service实现
 * @createDate 2022-08-01 14:06:02
 */
@Service
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise>
        implements MerchandiseService {
    
    @Autowired
    DishFlavorService dishFlavorService;
    
    /**
     * 分页查询
     *
     * @param merchandiseSearchDTO
     *
     * @return
     */
    @Override
    public IPage<Merchandise> search(MerchandiseSearchDTO merchandiseSearchDTO) {
        Page<Merchandise> page = new Page<>(merchandiseSearchDTO.getPage(), merchandiseSearchDTO.getSize());
        return this.baseMapper.search(page, merchandiseSearchDTO);
    }
    
    /**
     * 根据商品id 查询商品信息和口味信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public MerchandiseDataVO findById(Long id) {
        Merchandise merchandise = this.baseMapper.selectById(id);
        if (ObjectUtil.isNull(merchandise)) {
            return null;
        }
        
        MerchandiseDataVO merchandiseDataVO = new MerchandiseDataVO();
        BeanUtils.copyProperties(merchandise, merchandiseDataVO);
        List<DishFlavor> byDishId = dishFlavorService.findByDishId(merchandise.getId());
        
        if (ObjectUtil.isNotNull(byDishId)) {
            // 组装口味信息
            List<DishFlavorsDTO> dtoList = new ArrayList<>();
            for (DishFlavor dishFlavor : byDishId) {
                DishFlavorsDTO dishFlavorsDTO = new DishFlavorsDTO();
                dishFlavorsDTO.setName(dishFlavor.getName());
                dishFlavorsDTO.setInputValue("");
                dishFlavorsDTO.setInputVisible(false);
                
                List<String> values = JSONUtil.toList(dishFlavor.getValue(), String.class);
                
                String[] tags = values.toArray(new String[0]);
                
                dishFlavorsDTO.setTags(tags);
                
                dtoList.add(dishFlavorsDTO);
            }
            merchandiseDataVO.setDishFlavors(dtoList);
        }
        return merchandiseDataVO;
    }
}
