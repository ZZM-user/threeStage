package com.example.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author： 17602
 * @Date： 2022/8/18 20:40
 * @Desc：
 **/
@Getter
@Setter
@ToString
public class MerchandiseOfEnpVo {
    /**
     * 主键
     */
    private Long id;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品价格
     */
    private Double price;
    
    /**
     * 商品图片
     */
    private String picture;
    
    /**
     * 商品描述
     */
    private String description;
    
    /**
     * 商品综合评分
     */
    private Integer score;
    
    /**
     * 商家id
     */
    private Long b_id;
    
    /**
     * 商品分类
     */
    private Long m_id;
    
    /**
     * 是否上架 1：上架 ，0：没上架
     */
    private Integer isgrounding;
    
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
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;
    
    /**
     * 修改人
     */
    private String update_by;
    /**
     * 所属商家
     */
    private String enterprise_name;
    /**
     * 所属口味
     */
    private String category_name;
}
