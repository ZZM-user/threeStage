package com.example.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/2 10:44
 * @Desc： 输出格式
 **/
@Getter
@Setter
public class PageVo<T> {
    /**
     * 总行数
     */
    private Long totalRecord;
    
    /**
     * 当前页数据
     */
    private List<T> records;
    
    public static <T> PageVo<T> pageVo(IPage<T> iPage) {
        PageVo<T> pageVo = new PageVo<T>();
        pageVo.setTotalRecord(iPage.getTotal());
        pageVo.setRecords(iPage.getRecords());
        return pageVo;
    }
}
