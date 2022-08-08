package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 车型属性表
 *
 * @TableName car_model_attr_info
 */
@TableName(value = "car_model_attr_info")
@Data
public class CarModelAttrInfo implements Serializable {
    /**
     * 车型属性id
     */
    @TableId(value = "model_attr_id", type = IdType.AUTO)
    private Long model_attr_id;
    
    /**
     * 车型ID
     */
    @TableField(value = "model_id")
    private Long model_id;
    
    /**
     * 商品编码
     */
    @TableField(value = "goods_code")
    private String goods_code;
    
    /**
     * 属性值
     */
    @TableField(value = "attr_value")
    private String attr_value;
    
    /**
     * 二段编码
     */
    @TableField(value = "two_part_code")
    private String two_part_code;
    
    /**
     * 定金价格
     */
    @TableField(value = "down_price")
    private BigDecimal down_price;
    
    /**
     * 零售价格
     */
    @TableField(value = "sale_price")
    private BigDecimal sale_price;
    
    /**
     * 颜色编码
     */
    @TableField(value = "colour_code")
    private String colour_code;
    
    /**
     * 三段编码
     */
    @TableField(value = "thr_part_code")
    private String thr_part_code;
    
    /**
     *
     */
    @TableField(value = "create_by")
    private String create_by;
    
    /**
     *
     */
    @TableField(value = "create_time")
    private LocalDateTime create_time;
    
    /**
     *
     */
    @TableField(value = "update_by")
    private String update_by;
    
    /**
     *
     */
    @TableField(value = "update_time")
    private LocalDateTime update_time;
    
    /**
     * 删除标识：1：正常状态 2删除状态
     */
    @TableField(value = "del_flag")
    private String del_flag;
    
    /**
     * 车型属性预览图
     */
    @TableField(value = "preview_img")
    private String preview_img;
    
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CarModelAttrInfo other = (CarModelAttrInfo) that;
        return (this.getModel_attr_id() == null ? other.getModel_attr_id() == null : this.getModel_attr_id().equals(other.getModel_attr_id()))
                       && (this.getModel_id() == null ? other.getModel_id() == null : this.getModel_id().equals(other.getModel_id()))
                       && (this.getGoods_code() == null ? other.getGoods_code() == null : this.getGoods_code().equals(other.getGoods_code()))
                       && (this.getAttr_value() == null ? other.getAttr_value() == null : this.getAttr_value().equals(other.getAttr_value()))
                       && (this.getTwo_part_code() == null ? other.getTwo_part_code() == null : this.getTwo_part_code().equals(other.getTwo_part_code()))
                       && (this.getDown_price() == null ? other.getDown_price() == null : this.getDown_price().equals(other.getDown_price()))
                       && (this.getSale_price() == null ? other.getSale_price() == null : this.getSale_price().equals(other.getSale_price()))
                       && (this.getColour_code() == null ? other.getColour_code() == null : this.getColour_code().equals(other.getColour_code()))
                       && (this.getThr_part_code() == null ? other.getThr_part_code() == null : this.getThr_part_code().equals(other.getThr_part_code()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
                       && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                       && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
                       && (this.getPreview_img() == null ? other.getPreview_img() == null : this.getPreview_img().equals(other.getPreview_img()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getModel_attr_id() == null) ? 0 : getModel_attr_id().hashCode());
        result = prime * result + ((getModel_id() == null) ? 0 : getModel_id().hashCode());
        result = prime * result + ((getGoods_code() == null) ? 0 : getGoods_code().hashCode());
        result = prime * result + ((getAttr_value() == null) ? 0 : getAttr_value().hashCode());
        result = prime * result + ((getTwo_part_code() == null) ? 0 : getTwo_part_code().hashCode());
        result = prime * result + ((getDown_price() == null) ? 0 : getDown_price().hashCode());
        result = prime * result + ((getSale_price() == null) ? 0 : getSale_price().hashCode());
        result = prime * result + ((getColour_code() == null) ? 0 : getColour_code().hashCode());
        result = prime * result + ((getThr_part_code() == null) ? 0 : getThr_part_code().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getPreview_img() == null) ? 0 : getPreview_img().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", model_attr_id=").append(model_attr_id);
        sb.append(", model_id=").append(model_id);
        sb.append(", goods_code=").append(goods_code);
        sb.append(", attr_value=").append(attr_value);
        sb.append(", two_part_code=").append(two_part_code);
        sb.append(", down_price=").append(down_price);
        sb.append(", sale_price=").append(sale_price);
        sb.append(", colour_code=").append(colour_code);
        sb.append(", thr_part_code=").append(thr_part_code);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", preview_img=").append(preview_img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
