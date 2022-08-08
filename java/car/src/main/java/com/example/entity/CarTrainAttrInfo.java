package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 车系属性信息表
 *
 * @TableName car_train_attr_info
 */
@TableName(value = "car_train_attr_info")
@Data
public class CarTrainAttrInfo implements Serializable {
    /**
     * 车系属性ID
     */
    @TableId(value = "train_attr_id", type = IdType.AUTO)
    private Long train_attr_id;
    
    /**
     * 车系ID
     */
    @TableField(value = "train_id")
    private Long train_id;
    
    /**
     * 属性名字
     */
    @TableField(value = "attr_name")
    private String attr_name;
    
    /**
     * 属性展示名字
     */
    @TableField(value = "attr_show_name")
    private String attr_show_name;
    
    /**
     * 属性排序
     */
    @TableField(value = "sort")
    private Integer sort;
    
    /**
     * 是否必填 1必填 0非必填
     */
    @TableField(value = "is_must")
    private String is_must;
    
    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
    
    /**
     *
     */
    @TableField(value = "create_by")
    private String create_by;
    
    /**
     *
     */
    @TableField(value = "create_time")
    private LocalDateTime create_time;
    
    /**
     *
     */
    @TableField(value = "update_by")
    private String update_by;
    
    /**
     *
     */
    @TableField(value = "update_man")
    private LocalDateTime update_man;
    
    /**
     * 删除标识:1正常状态 2 删除状态
     */
    @TableField(value = "del_flag")
    private String del_flag;
    
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
        return (this.getTrain_attr_id() == null ? other.getTrain_attr_id() == null : this.getTrain_attr_id().equals(other.getTrain_attr_id()))
                       && (this.getTrain_id() == null ? other.getTrain_id() == null : this.getTrain_id().equals(other.getTrain_id()))
                       && (this.getAttr_name() == null ? other.getAttr_name() == null : this.getAttr_name().equals(other.getAttr_name()))
                       && (this.getAttr_show_name() == null ? other.getAttr_show_name() == null : this.getAttr_show_name().equals(other.getAttr_show_name()))
                       && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                       && (this.getIs_must() == null ? other.getIs_must() == null : this.getIs_must().equals(other.getIs_must()))
                       && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
                       && (this.getUpdate_man() == null ? other.getUpdate_man() == null : this.getUpdate_man().equals(other.getUpdate_man()))
                       && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrain_attr_id() == null) ? 0 : getTrain_attr_id().hashCode());
        result = prime * result + ((getTrain_id() == null) ? 0 : getTrain_id().hashCode());
        result = prime * result + ((getAttr_name() == null) ? 0 : getAttr_name().hashCode());
        result = prime * result + ((getAttr_show_name() == null) ? 0 : getAttr_show_name().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getIs_must() == null) ? 0 : getIs_must().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_man() == null) ? 0 : getUpdate_man().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", train_attr_id=").append(train_attr_id);
        sb.append(", train_id=").append(train_id);
        sb.append(", attr_name=").append(attr_name);
        sb.append(", attr_show_name=").append(attr_show_name);
        sb.append(", sort=").append(sort);
        sb.append(", is_must=").append(is_must);
        sb.append(", remark=").append(remark);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_man=").append(update_man);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
