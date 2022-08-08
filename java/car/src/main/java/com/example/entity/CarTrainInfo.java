package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 车系信息表
 *
 * @TableName car_train_info
 */
@TableName(value = "car_train_info")
@Data
public class CarTrainInfo implements Serializable {
    /**
     * 车系ID
     */
    @TableId(value = "train_id", type = IdType.AUTO)
    private Long train_id;
    
    /**
     * 品牌编码
     */
    @TableField(value = "brand_code")
    private String brand_code;
    
    /**
     * 车系名称
     */
    @TableField(value = "train_name")
    private String train_name;
    
    /**
     * 商品编码
     */
    @TableField(value = "goods_code")
    private String goods_code;
    
    /**
     * 车系代码
     */
    @TableField(value = "train_code")
    private String train_code;
    
    /**
     * 车系图片
     */
    @TableField(value = "train_img")
    private String train_img;
    
    /**
     * 1：正常0无效
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
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
    
    /**
     * 删除标志 1正常 2 表示删除
     */
    @TableField(value = "del_flag")
    private String del_flag;
    
    /**
     * 处理渠道
     */
    @TableField(value = "channel_name")
    private String channel_name;
    
    /**
     * 处理渠道编码
     */
    @TableField(value = "channel_code")
    private String channel_code;
    
    /**
     * 品牌
     */
    @TableField(value = "brand")
    private String brand;
    
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
        CarTrainInfo other = (CarTrainInfo) that;
        return (this.getTrain_id() == null ? other.getTrain_id() == null : this.getTrain_id().equals(other.getTrain_id()))
                       && (this.getBrand_code() == null ? other.getBrand_code() == null : this.getBrand_code().equals(other.getBrand_code()))
                       && (this.getTrain_name() == null ? other.getTrain_name() == null : this.getTrain_name().equals(other.getTrain_name()))
                       && (this.getGoods_code() == null ? other.getGoods_code() == null : this.getGoods_code().equals(other.getGoods_code()))
                       && (this.getTrain_code() == null ? other.getTrain_code() == null : this.getTrain_code().equals(other.getTrain_code()))
                       && (this.getTrain_img() == null ? other.getTrain_img() == null : this.getTrain_img().equals(other.getTrain_img()))
                       && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
                       && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                       && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                       && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
                       && (this.getChannel_name() == null ? other.getChannel_name() == null : this.getChannel_name().equals(other.getChannel_name()))
                       && (this.getChannel_code() == null ? other.getChannel_code() == null : this.getChannel_code().equals(other.getChannel_code()))
                       && (this.getBrand() == null ? other.getBrand() == null : this.getBrand().equals(other.getBrand()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrain_id() == null) ? 0 : getTrain_id().hashCode());
        result = prime * result + ((getBrand_code() == null) ? 0 : getBrand_code().hashCode());
        result = prime * result + ((getTrain_name() == null) ? 0 : getTrain_name().hashCode());
        result = prime * result + ((getGoods_code() == null) ? 0 : getGoods_code().hashCode());
        result = prime * result + ((getTrain_code() == null) ? 0 : getTrain_code().hashCode());
        result = prime * result + ((getTrain_img() == null) ? 0 : getTrain_img().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getChannel_name() == null) ? 0 : getChannel_name().hashCode());
        result = prime * result + ((getChannel_code() == null) ? 0 : getChannel_code().hashCode());
        result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", train_id=").append(train_id);
        sb.append(", brand_code=").append(brand_code);
        sb.append(", train_name=").append(train_name);
        sb.append(", goods_code=").append(goods_code);
        sb.append(", train_code=").append(train_code);
        sb.append(", train_img=").append(train_img);
        sb.append(", status=").append(status);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", remark=").append(remark);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", channel_name=").append(channel_name);
        sb.append(", channel_code=").append(channel_code);
        sb.append(", brand=").append(brand);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
