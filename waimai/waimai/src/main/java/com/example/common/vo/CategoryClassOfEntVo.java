package com.example.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author： 17602
 * @Date： 2022/8/18 22:10
 * @Desc：
 **/
@Getter
@Setter
@ToString
public class CategoryClassOfEntVo {
    /**
     * 主键
     */
    private Long id;
    
    /**
     * 商家id
     */
    private Long enterprise_id;
    
    /**
     * 类别名称
     */
    private String name;
    
    /**
     * 口味图片
     */
    private String picture;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    
    /**
     * 创建人
     */
    private String create_by;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;
    
    /**
     * 更新人
     */
    private String update_by;
    
    /**
     * 所属商家
     */
    private String enterprise_name;
}
