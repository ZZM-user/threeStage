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
 * 车型属性表
 * @TableName car_model_attr_info
 */
@TableName(value ="car_model_attr_info")
@Data
public class CarModelAttrInfo implements Serializable {
    /**
     * 车型属性id
     */
    @TableId(value = "model_attr_id", type = IdType.AUTO)
    private Long modelAttrId;

    /**
     * 车型ID
     */
    @TableField(value = "model_id")
    private Long modelId;

    /**
     * 商品编码
     */
    @TableField(value = "goods_code")
    private String goodsCode;

    /**
     * 属性值
     */
    @TableField(value = "attr_value")
    private String attrValue;

    /**
     * 二段编码
     */
    @TableField(value = "two_part_code")
    private String twoPartCode;

    /**
     * 定金价格
     */
    @TableField(value = "down_price")
    private BigDecimal downPrice;

    /**
     * 零售价格
     */
    @TableField(value = "sale_price")
    private BigDecimal salePrice;

    /**
     * 颜色编码
     */
    @TableField(value = "colour_code")
    private String colourCode;

    /**
     * 三段编码
     */
    @TableField(value = "thr_part_code")
    private String thrPartCode;

    /**
     * 
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标识：1：正常状态 2删除状态
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 车型属性预览图
     */
    @TableField(value = "preview_img")
    private String previewImg;

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
        return (this.getModelAttrId() == null ? other.getModelAttrId() == null : this.getModelAttrId().equals(other.getModelAttrId()))
            && (this.getModelId() == null ? other.getModelId() == null : this.getModelId().equals(other.getModelId()))
            && (this.getGoodsCode() == null ? other.getGoodsCode() == null : this.getGoodsCode().equals(other.getGoodsCode()))
            && (this.getAttrValue() == null ? other.getAttrValue() == null : this.getAttrValue().equals(other.getAttrValue()))
            && (this.getTwoPartCode() == null ? other.getTwoPartCode() == null : this.getTwoPartCode().equals(other.getTwoPartCode()))
            && (this.getDownPrice() == null ? other.getDownPrice() == null : this.getDownPrice().equals(other.getDownPrice()))
            && (this.getSalePrice() == null ? other.getSalePrice() == null : this.getSalePrice().equals(other.getSalePrice()))
            && (this.getColourCode() == null ? other.getColourCode() == null : this.getColourCode().equals(other.getColourCode()))
            && (this.getThrPartCode() == null ? other.getThrPartCode() == null : this.getThrPartCode().equals(other.getThrPartCode()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getPreviewImg() == null ? other.getPreviewImg() == null : this.getPreviewImg().equals(other.getPreviewImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getModelAttrId() == null) ? 0 : getModelAttrId().hashCode());
        result = prime * result + ((getModelId() == null) ? 0 : getModelId().hashCode());
        result = prime * result + ((getGoodsCode() == null) ? 0 : getGoodsCode().hashCode());
        result = prime * result + ((getAttrValue() == null) ? 0 : getAttrValue().hashCode());
        result = prime * result + ((getTwoPartCode() == null) ? 0 : getTwoPartCode().hashCode());
        result = prime * result + ((getDownPrice() == null) ? 0 : getDownPrice().hashCode());
        result = prime * result + ((getSalePrice() == null) ? 0 : getSalePrice().hashCode());
        result = prime * result + ((getColourCode() == null) ? 0 : getColourCode().hashCode());
        result = prime * result + ((getThrPartCode() == null) ? 0 : getThrPartCode().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getPreviewImg() == null) ? 0 : getPreviewImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", modelAttrId=").append(modelAttrId);
        sb.append(", modelId=").append(modelId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", attrValue=").append(attrValue);
        sb.append(", twoPartCode=").append(twoPartCode);
        sb.append(", downPrice=").append(downPrice);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", colourCode=").append(colourCode);
        sb.append(", thrPartCode=").append(thrPartCode);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", previewImg=").append(previewImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}