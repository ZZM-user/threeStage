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
 * 车型信息表
 *
 * @TableName car_model_info
 */
@TableName(value = "car_model_info")
@Data
public class CarModelInfo implements Serializable {
    /**
     *
     */
    @TableId(value = "model_id", type = IdType.AUTO)
    private Long model_id;
    
    /**
     * 品牌编码
     */
    @TableField(value = "brand_code")
    private String brand_code;
    
    /**
     * 车系id
     */
    @TableField(value = "train_id")
    private Long train_id;
    
    /**
     * 车型名称
     */
    @TableField(value = "model_name")
    private String model_name;
    
    /**
     * 车型图片
     */
    @TableField(value = "model_img")
    private String model_img;
    
    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;
    
    /**
     * 1:有效0:无效
     */
    @TableField(value = "status")
    private String status;
    
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
     * 修改人
     */
    @TableField(value = "update_by")
    private String update_by;
    
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime update_time;
    
    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
    
    /**
     * 删除标志：1正常2删除
     */
    @TableField(value = "del_flag")
    private String del_flag;
    
    /**
     * 价格
     */
    @TableField(value = "model_price")
    private BigDecimal model_price;
    
    /**
     * 子图片
     */
    @TableField(value = "model_sub_img")
    private String model_sub_img;
    
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
        CarModelInfo other = (CarModelInfo) that;
        return (this.getModel_id() == null ? other.getModel_id() == null : this.getModel_id().equals(other.getModel_id()))
                       && (this.getBrand_code() == null ? other.getBrand_code() == null : this.getBrand_code().equals(other.getBrand_code()))
                       && (this.getTrain_id() == null ? other.getTrain_id() == null : this.getTrain_id().equals(other.getTrain_id()))
                       && (this.getModel_name() == null ? other.getModel_name() == null : this.getModel_name().equals(other.getModel_name()))
                       && (this.getModel_img() == null ? other.getModel_img() == null : this.getModel_img().equals(other.getModel_img()))
                       && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                       && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
                       && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                       && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                       && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
                       && (this.getModel_price() == null ? other.getModel_price() == null : this.getModel_price().equals(other.getModel_price()))
                       && (this.getModel_sub_img() == null ? other.getModel_sub_img() == null : this.getModel_sub_img().equals(other.getModel_sub_img()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getModel_id() == null) ? 0 : getModel_id().hashCode());
        result = prime * result + ((getBrand_code() == null) ? 0 : getBrand_code().hashCode());
        result = prime * result + ((getTrain_id() == null) ? 0 : getTrain_id().hashCode());
        result = prime * result + ((getModel_name() == null) ? 0 : getModel_name().hashCode());
        result = prime * result + ((getModel_img() == null) ? 0 : getModel_img().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getModel_price() == null) ? 0 : getModel_price().hashCode());
        result = prime * result + ((getModel_sub_img() == null) ? 0 : getModel_sub_img().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", model_id=").append(model_id);
        sb.append(", brand_code=").append(brand_code);
        sb.append(", train_id=").append(train_id);
        sb.append(", model_name=").append(model_name);
        sb.append(", model_img=").append(model_img);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", remark=").append(remark);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", model_price=").append(model_price);
        sb.append(", model_sub_img=").append(model_sub_img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
