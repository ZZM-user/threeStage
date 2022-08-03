package com.example.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/2 10:44
 * @Desc： 输出格式
 **/
@ApiModel(value = "分页输出模型")
@Getter
@Setter
public class PageVo<T> {
    /**
     * 总行数
     */
    private int totalRecord;
    
    /**
     * 当前页数据
     */
    private List<T> records;
    
    public static <T> PageVo<T> pageVo(Page<T> iPage) {
        PageVo<T> pageVo = new PageVo<T>();
        pageVo.setTotalRecord(iPage.getTotalPages());
        pageVo.setRecords(iPage.getContent());
        return pageVo;
    }
}
