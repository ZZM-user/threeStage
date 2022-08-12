package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车系信息表
 * @TableName car_train_info
 */
@TableName(value ="car_train_info")
@Data
public class CarTrainInfo implements Serializable {
    /**
     * 车系ID
     */
    @TableId(value = "train_id", type = IdType.AUTO)
    private Long trainId;

    /**
     * 品牌编码
     */
    @TableField(value = "brand_code")
    private String brandCode;

    /**
     * 车系名称
     */
    @TableField(value = "train_name")
    private String trainName;

    /**
     * 商品编码
     */
    @TableField(value = "goods_code")
    private String goodsCode;

    /**
     * 车系代码
     */
    @TableField(value = "train_code")
    private String trainCode;

    /**
     * 车系图片
     */
    @TableField(value = "train_img")
    private String trainImg;

    /**
     * 1：正常0无效
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
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 删除标志 1正常 2 表示删除
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 处理渠道
     */
    @TableField(value = "channel_name")
    private String channelName;

    /**
     * 处理渠道编码
     */
    @TableField(value = "channel_code")
    private String channelCode;

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
        return (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getBrandCode() == null ? other.getBrandCode() == null : this.getBrandCode().equals(other.getBrandCode()))
            && (this.getTrainName() == null ? other.getTrainName() == null : this.getTrainName().equals(other.getTrainName()))
            && (this.getGoodsCode() == null ? other.getGoodsCode() == null : this.getGoodsCode().equals(other.getGoodsCode()))
            && (this.getTrainCode() == null ? other.getTrainCode() == null : this.getTrainCode().equals(other.getTrainCode()))
            && (this.getTrainImg() == null ? other.getTrainImg() == null : this.getTrainImg().equals(other.getTrainImg()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getChannelName() == null ? other.getChannelName() == null : this.getChannelName().equals(other.getChannelName()))
            && (this.getChannelCode() == null ? other.getChannelCode() == null : this.getChannelCode().equals(other.getChannelCode()))
            && (this.getBrand() == null ? other.getBrand() == null : this.getBrand().equals(other.getBrand()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getBrandCode() == null) ? 0 : getBrandCode().hashCode());
        result = prime * result + ((getTrainName() == null) ? 0 : getTrainName().hashCode());
        result = prime * result + ((getGoodsCode() == null) ? 0 : getGoodsCode().hashCode());
        result = prime * result + ((getTrainCode() == null) ? 0 : getTrainCode().hashCode());
        result = prime * result + ((getTrainImg() == null) ? 0 : getTrainImg().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getChannelName() == null) ? 0 : getChannelName().hashCode());
        result = prime * result + ((getChannelCode() == null) ? 0 : getChannelCode().hashCode());
        result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trainId=").append(trainId);
        sb.append(", brandCode=").append(brandCode);
        sb.append(", trainName=").append(trainName);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", trainCode=").append(trainCode);
        sb.append(", trainImg=").append(trainImg);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", channelName=").append(channelName);
        sb.append(", channelCode=").append(channelCode);
        sb.append(", brand=").append(brand);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}