package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车系属性信息表
 * @TableName car_train_attr_info
 */
@TableName(value ="car_train_attr_info")
@Data
public class CarTrainAttrInfo implements Serializable {
    /**
     * 车系属性ID
     */
    @TableId(value = "train_attr_id", type = IdType.AUTO)
    private Long trainAttrId;

    /**
     * 车系ID
     */
    @TableField(value = "train_id")
    private Long trainId;

    /**
     * 属性名字
     */
    @TableField(value = "attr_name")
    private String attrName;

    /**
     * 属性展示名字
     */
    @TableField(value = "attr_show_name")
    private String attrShowName;

    /**
     * 属性排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 是否必填 1必填 0非必填
     */
    @TableField(value = "is_must")
    private String isMust;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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
    @TableField(value = "update_man")
    private Date updateMan;

    /**
     * 删除标识:1正常状态 2 删除状态  
     */
    @TableField(value = "del_flag")
    private String delFlag;

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
        CarTrainAttrInfo other = (CarTrainAttrInfo) that;
        return (this.getTrainAttrId() == null ? other.getTrainAttrId() == null : this.getTrainAttrId().equals(other.getTrainAttrId()))
            && (this.getTrainId() == null ? other.getTrainId() == null : this.getTrainId().equals(other.getTrainId()))
            && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
            && (this.getAttrShowName() == null ? other.getAttrShowName() == null : this.getAttrShowName().equals(other.getAttrShowName()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getIsMust() == null ? other.getIsMust() == null : this.getIsMust().equals(other.getIsMust()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateMan() == null ? other.getUpdateMan() == null : this.getUpdateMan().equals(other.getUpdateMan()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrainAttrId() == null) ? 0 : getTrainAttrId().hashCode());
        result = prime * result + ((getTrainId() == null) ? 0 : getTrainId().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getAttrShowName() == null) ? 0 : getAttrShowName().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getIsMust() == null) ? 0 : getIsMust().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateMan() == null) ? 0 : getUpdateMan().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trainAttrId=").append(trainAttrId);
        sb.append(", trainId=").append(trainId);
        sb.append(", attrName=").append(attrName);
        sb.append(", attrShowName=").append(attrShowName);
        sb.append(", sort=").append(sort);
        sb.append(", isMust=").append(isMust);
        sb.append(", remark=").append(remark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateMan=").append(updateMan);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}