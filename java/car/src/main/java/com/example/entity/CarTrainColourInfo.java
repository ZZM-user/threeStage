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
 * 车系颜色表
 *
 * @TableName car_train_colour_info
 */
@TableName(value = "car_train_colour_info")
@Data
public class CarTrainColourInfo implements Serializable {
    /**
     * 车系颜色ID
     */
    @TableId(value = "train_colour_id", type = IdType.AUTO)
    private Long train_colour_id;
    
    /**
     * 车系ID
     */
    @TableField(value = "train_id")
    private Long train_id;
    
    /**
     * 颜色名字
     */
    @TableField(value = "colour_name")
    private String colour_name;
    
    /**
     * 颜色编码
     */
    @TableField(value = "colour_code")
    private String colour_code;
    
    /**
     * 颜色图片
     */
    @TableField(value = "colour_img")
    private String colour_img;
    
    /**
     * 子图片
     */
    @TableField(value = "colour_sub_img")
    private String colour_sub_img;
    
    /**
     * 价格
     */
    @TableField(value = "colour_price")
    private BigDecimal colour_price;
    
    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;
    
    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String create_by;
    
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime create_time;
    
    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String update_by;
    
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime update_time;
    
    /**
     * 删除标注 1 正常状态 2 删除状态
     */
    @TableField(value = "del_flag")
    private String del_flag;
    
    /**
     * 颜色限制车型id
     */
    @TableField(value = "value_code")
    private String value_code;
    
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
        CarTrainColourInfo other = (CarTrainColourInfo) that;
        return (this.getTrain_colour_id() == null ? other.getTrain_colour_id() == null : this.getTrain_colour_id().equals(other.getTrain_colour_id()))
                       && (this.getTrain_id() == null ? other.getTrain_id() == null : this.getTrain_id().equals(other.getTrain_id()))
                       && (this.getColour_name() == null ? other.getColour_name() == null : this.getColour_name().equals(other.getColour_name()))
                       && (this.getColour_code() == null ? other.getColour_code() == null : this.getColour_code().equals(other.getColour_code()))
                       && (this.getColour_img() == null ? other.getColour_img() == null : this.getColour_img().equals(other.getColour_img()))
                       && (this.getColour_sub_img() == null ? other.getColour_sub_img() == null : this.getColour_sub_img().equals(other.getColour_sub_img()))
                       && (this.getColour_price() == null ? other.getColour_price() == null : this.getColour_price().equals(other.getColour_price()))
                       && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
                       && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                       && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
                       && (this.getValue_code() == null ? other.getValue_code() == null : this.getValue_code().equals(other.getValue_code()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrain_colour_id() == null) ? 0 : getTrain_colour_id().hashCode());
        result = prime * result + ((getTrain_id() == null) ? 0 : getTrain_id().hashCode());
        result = prime * result + ((getColour_name() == null) ? 0 : getColour_name().hashCode());
        result = prime * result + ((getColour_code() == null) ? 0 : getColour_code().hashCode());
        result = prime * result + ((getColour_img() == null) ? 0 : getColour_img().hashCode());
        result = prime * result + ((getColour_sub_img() == null) ? 0 : getColour_sub_img().hashCode());
        result = prime * result + ((getColour_price() == null) ? 0 : getColour_price().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getValue_code() == null) ? 0 : getValue_code().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", train_colour_id=").append(train_colour_id);
        sb.append(", train_id=").append(train_id);
        sb.append(", colour_name=").append(colour_name);
        sb.append(", colour_code=").append(colour_code);
        sb.append(", colour_img=").append(colour_img);
        sb.append(", colour_sub_img=").append(colour_sub_img);
        sb.append(", colour_price=").append(colour_price);
        sb.append(", sort=").append(sort);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", value_code=").append(value_code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
