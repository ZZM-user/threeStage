package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.domain.R;
import com.example.common.vo.PageVo;
import com.example.dto.DscDealerSearchDTO;
import com.example.entity.DcsDealer;
import com.example.service.DcsDealerIService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/8/5 14:24
 * @Desc： 汽车经销商
 **/
@Api(value = "汽车经销商")
@CrossOrigin
@RestController
@RequestMapping("/api/dealer")
public class DscDealerController {
    @Autowired
    DcsDealerIService service;
    
    @GetMapping("/data")
    public R search(DscDealerSearchDTO dscDealerSearchDTO) {
        IPage<DcsDealer> search = service.search(dscDealerSearchDTO);
        PageVo<DcsDealer> dcsDealerPageVo = PageVo.pageVo(search);
        return R.okHasData(dcsDealerPageVo);
    }
    
}
