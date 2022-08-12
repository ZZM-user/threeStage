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
 * 车型信息表
 * @TableName car_model_info
 */
@TableName(value ="car_model_info")
@Data
public class CarModelInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "model_id", type = IdType.AUTO)
    private Long modelId;

    /**
     * 品牌编码
     */
    @TableField(value = "brand_code")
    private String brandCode;

    /**
     * 车系id
     */
    @TableField(value = "train_id")
    private Long trainId;

    /**
     * 车型名称
     */
    @TableField(value = "model_name")
    private String modelName;

    /**
     * 车型图片
     */
    @TableField(value = "model_img")
    private String modelImg;

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
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 删除标志：1正常2删除
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 价格
     */
    @TableField(value = "model_price")
    private BigDecimal modelPrice;

    /**
     * 子图片
     */
    @TableField(value = "model_sub_img")
    private String modelSubImg;

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
        return (this.getModelId() == null ? other.getModelId() == null : this.getModelId().equals(other.getModelId()))
            && (this.getBrandCode() == null ? other.getBrandCode() == null : this.getBrandCode().equals(other.getBrandCode()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getModelName() == null ? other.getModelName() == null : this.getModelName().equals(other.getModelName()))
            && (this.getModelImg() == null ? other.getModelImg() == null : this.getModelImg().equals(other.getModelImg()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getModelPrice() == null ? other.getModelPrice() == null : this.getModelPrice().equals(other.getModelPrice()))
            && (this.getModelSubImg() == null ? other.getModelSubImg() == null : this.getModelSubImg().equals(other.getModelSubImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getModelId() == null) ? 0 : getModelId().hashCode());
        result = prime * result + ((getBrandCode() == null) ? 0 : getBrandCode().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getModelName() == null) ? 0 : getModelName().hashCode());
        result = prime * result + ((getModelImg() == null) ? 0 : getModelImg().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getModelPrice() == null) ? 0 : getModelPrice().hashCode());
        result = prime * result + ((getModelSubImg() == null) ? 0 : getModelSubImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", modelId=").append(modelId);
        sb.append(", brandCode=").append(brandCode);
        sb.append(", trainId=").append(trainId);
        sb.append(", modelName=").append(modelName);
        sb.append(", modelImg=").append(modelImg);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", modelPrice=").append(modelPrice);
        sb.append(", modelSubImg=").append(modelSubImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}