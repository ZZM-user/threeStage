package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName record
 */
@TableName(value = "record")
@Data
public class Record implements Serializable {
    /**
     * 记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 入住员工id
     */
    @TableField(value = "e_id")
    private Integer eId;
    
    /**
     * 入住房间id
     */
    @TableField(value = "r_id")
    private Integer rId;
    
    /**
     * 入住时间
     */
    @TableField(value = "enter_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date enterDate;
    
    /**
     * 预计退房时间
     */
    @TableField(value = "leave_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date leaveDate;
    
    /**
     * 实际退房时间
     */
    @TableField(value = "reality_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date realityDate;
    
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eId=").append(eId);
        sb.append(", rId=").append(rId);
        sb.append(", enterDate=").append(enterDate);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", realityDate=").append(realityDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
