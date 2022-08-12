package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 车系属性值表
 * @TableName car_train_attr_value_info
 */
@TableName(value ="car_train_attr_value_info")
@Data
public class CarTrainAttrValueInfo implements Serializable {
    /**
     * 车系属性值ID
     */
    @TableId(value = "train_value_id", type = IdType.AUTO)
    private Long trainValueId;

    /**
     * 车系ID
     */
    @TableField(value = "train_id")
    private Long trainId;

    /**
     * 车系属性ID
     */
    @TableField(value = "train_attr_id")
    private Long trainAttrId;

    /**
     * 车系属性值名字
     */
    @TableField(value = "value_name")
    private String valueName;

    /**
     * 车系属性展示值
     */
    @TableField(value = "show_name")
    private String showName;

    /**
     * 车系属性值编码
     */
    @TableField(value = "value_code")
    private String valueCode;

    /**
     * 价格（元）
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 图片
     */
    @TableField(value = "value_img")
    private String valueImg;

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
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标识： 1：正常状态 2 删除状态
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 1内饰颜色；2选装包；
     */
    @TableField(value = "astrict")
    private Integer astrict;

    /**
     * 子图片
     */
    @TableField(value = "value_sub_img")
    private String valueSubImg;

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
        return (this.getTrainValueId() == null ? other.getTrainValueId() == null : this.getTrainValueId().equals(other.getTrainValueId()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getTrainAttrId() == null ? other.getTrainAttrId() == null : this.getTrainAttrId().equals(other.getTrainAttrId()))
            && (this.getValueName() == null ? other.getValueName() == null : this.getValueName().equals(other.getValueName()))
            && (this.getShowName() == null ? other.getShowName() == null : this.getShowName().equals(other.getShowName()))
            && (this.getValueCode() == null ? other.getValueCode() == null : this.getValueCode().equals(other.getValueCode()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getValueImg() == null ? other.getValueImg() == null : this.getValueImg().equals(other.getValueImg()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getAstrict() == null ? other.getAstrict() == null : this.getAstrict().equals(other.getAstrict()))
            && (this.getValueSubImg() == null ? other.getValueSubImg() == null : this.getValueSubImg().equals(other.getValueSubImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrainValueId() == null) ? 0 : getTrainValueId().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getTrainAttrId() == null) ? 0 : getTrainAttrId().hashCode());
        result = prime * result + ((getValueName() == null) ? 0 : getValueName().hashCode());
        result = prime * result + ((getShowName() == null) ? 0 : getShowName().hashCode());
        result = prime * result + ((getValueCode() == null) ? 0 : getValueCode().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getValueImg() == null) ? 0 : getValueImg().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getAstrict() == null) ? 0 : getAstrict().hashCode());
        result = prime * result + ((getValueSubImg() == null) ? 0 : getValueSubImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trainValueId=").append(trainValueId);
        sb.append(", trainId=").append(trainId);
        sb.append(", trainAttrId=").append(trainAttrId);
        sb.append(", valueName=").append(valueName);
        sb.append(", showName=").append(showName);
        sb.append(", valueCode=").append(valueCode);
        sb.append(", price=").append(price);
        sb.append(", valueImg=").append(valueImg);
        sb.append(", sort=").append(sort);
        sb.append(", remark=").append(remark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", astrict=").append(astrict);
        sb.append(", valueSubImg=").append(valueSubImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}