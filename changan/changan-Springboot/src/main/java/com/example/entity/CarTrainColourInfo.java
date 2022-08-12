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
 * 车系颜色表
 * @TableName car_train_colour_info
 */
@TableName(value ="car_train_colour_info")
@Data
public class CarTrainColourInfo implements Serializable {
    /**
     * 车系颜色ID
     */
    @TableId(value = "train_colour_id", type = IdType.AUTO)
    private Long trainColourId;

    /**
     * 车系ID
     */
    @TableField(value = "train_id")
    private Long trainId;

    /**
     * 颜色名字
     */
    @TableField(value = "colour_name")
    private String colourName;

    /**
     * 颜色编码
     */
    @TableField(value = "colour_code")
    private String colourCode;

    /**
     * 颜色图片
     */
    @TableField(value = "colour_img")
    private String colourImg;

    /**
     * 子图片
     */
    @TableField(value = "colour_sub_img")
    private String colourSubImg;

    /**
     * 价格
     */
    @TableField(value = "colour_price")
    private BigDecimal colourPrice;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

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
     * 删除标注 1 正常状态 2 删除状态
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 颜色限制车型id
     */
    @TableField(value = "value_code")
    private String valueCode;

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
        return (this.getTrainColourId() == null ? other.getTrainColourId() == null : this.getTrainColourId().equals(other.getTrainColourId()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getColourName() == null ? other.getColourName() == null : this.getColourName().equals(other.getColourName()))
            && (this.getColourCode() == null ? other.getColourCode() == null : this.getColourCode().equals(other.getColourCode()))
            && (this.getColourImg() == null ? other.getColourImg() == null : this.getColourImg().equals(other.getColourImg()))
            && (this.getColourSubImg() == null ? other.getColourSubImg() == null : this.getColourSubImg().equals(other.getColourSubImg()))
            && (this.getColourPrice() == null ? other.getColourPrice() == null : this.getColourPrice().equals(other.getColourPrice()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getValueCode() == null ? other.getValueCode() == null : this.getValueCode().equals(other.getValueCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrainColourId() == null) ? 0 : getTrainColourId().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getColourName() == null) ? 0 : getColourName().hashCode());
        result = prime * result + ((getColourCode() == null) ? 0 : getColourCode().hashCode());
        result = prime * result + ((getColourImg() == null) ? 0 : getColourImg().hashCode());
        result = prime * result + ((getColourSubImg() == null) ? 0 : getColourSubImg().hashCode());
        result = prime * result + ((getColourPrice() == null) ? 0 : getColourPrice().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getValueCode() == null) ? 0 : getValueCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trainColourId=").append(trainColourId);
        sb.append(", trainId=").append(trainId);
        sb.append(", colourName=").append(colourName);
        sb.append(", colourCode=").append(colourCode);
        sb.append(", colourImg=").append(colourImg);
        sb.append(", colourSubImg=").append(colourSubImg);
        sb.append(", colourPrice=").append(colourPrice);
        sb.append(", sort=").append(sort);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", valueCode=").append(valueCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}