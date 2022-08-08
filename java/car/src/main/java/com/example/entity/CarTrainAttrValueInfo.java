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
 * 车系属性值表
 *
 * @TableName car_train_attr_value_info
 */
@TableName(value = "car_train_attr_value_info")
@Data
public class CarTrainAttrValueInfo implements Serializable {
    /**
     * 车系属性值ID
     */
    @TableId(value = "train_value_id", type = IdType.AUTO)
    private Long train_value_id;
    
    /**
     * 车系ID
     */
    @TableField(value = "train_id")
    private Long train_id;
    
    /**
     * 车系属性ID
     */
    @TableField(value = "train_attr_id")
    private Long train_attr_id;
    
    /**
     * 车系属性值名字
     */
    @TableField(value = "value_name")
    private String value_name;
    
    /**
     * 车系属性展示值
     */
    @TableField(value = "show_name")
    private String show_name;
    
    /**
     * 车系属性值编码
     */
    @TableField(value = "value_code")
    private String value_code;
    
    /**
     * 价格（元）
     */
    @TableField(value = "price")
    private BigDecimal price;
    
    /**
     * 图片
     */
    @TableField(value = "value_img")
    private String value_img;
    
    /**
     *
     */
    @TableField(value = "sort")
    private Integer sort;
    
    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
    
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
     * 删除标识： 1：正常状态 2 删除状态
     */
    @TableField(value = "del_flag")
    private String del_flag;
    
    /**
     * 1内饰颜色；2选装包；
     */
    @TableField(value = "astrict")
    private Integer astrict;
    
    /**
     * 子图片
     */
    @TableField(value = "value_sub_img")
    private String value_sub_img;
    
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
        CarTrainAttrValueInfo other = (CarTrainAttrValueInfo) that;
        return (this.getTrain_value_id() == null ? other.getTrain_value_id() == null : this.getTrain_value_id().equals(other.getTrain_value_id()))
                       && (this.getTrain_id() == null ? other.getTrain_id() == null : this.getTrain_id().equals(other.getTrain_id()))
                       && (this.getTrain_attr_id() == null ? other.getTrain_attr_id() == null : this.getTrain_attr_id().equals(other.getTrain_attr_id()))
                       && (this.getValue_name() == null ? other.getValue_name() == null : this.getValue_name().equals(other.getValue_name()))
                       && (this.getShow_name() == null ? other.getShow_name() == null : this.getShow_name().equals(other.getShow_name()))
                       && (this.getValue_code() == null ? other.getValue_code() == null : this.getValue_code().equals(other.getValue_code()))
                       && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                       && (this.getValue_img() == null ? other.getValue_img() == null : this.getValue_img().equals(other.getValue_img()))
                       && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                       && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
                       && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                       && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
                       && (this.getAstrict() == null ? other.getAstrict() == null : this.getAstrict().equals(other.getAstrict()))
                       && (this.getValue_sub_img() == null ? other.getValue_sub_img() == null : this.getValue_sub_img().equals(other.getValue_sub_img()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrain_value_id() == null) ? 0 : getTrain_value_id().hashCode());
        result = prime * result + ((getTrain_id() == null) ? 0 : getTrain_id().hashCode());
        result = prime * result + ((getTrain_attr_id() == null) ? 0 : getTrain_attr_id().hashCode());
        result = prime * result + ((getValue_name() == null) ? 0 : getValue_name().hashCode());
        result = prime * result + ((getShow_name() == null) ? 0 : getShow_name().hashCode());
        result = prime * result + ((getValue_code() == null) ? 0 : getValue_code().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getValue_img() == null) ? 0 : getValue_img().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getAstrict() == null) ? 0 : getAstrict().hashCode());
        result = prime * result + ((getValue_sub_img() == null) ? 0 : getValue_sub_img().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", train_value_id=").append(train_value_id);
        sb.append(", train_id=").append(train_id);
        sb.append(", train_attr_id=").append(train_attr_id);
        sb.append(", value_name=").append(value_name);
        sb.append(", show_name=").append(show_name);
        sb.append(", value_code=").append(value_code);
        sb.append(", price=").append(price);
        sb.append(", value_img=").append(value_img);
        sb.append(", sort=").append(sort);
        sb.append(", remark=").append(remark);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", astrict=").append(astrict);
        sb.append(", value_sub_img=").append(value_sub_img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
