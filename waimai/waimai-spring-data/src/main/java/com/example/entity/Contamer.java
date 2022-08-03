package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 前端用户
 *
 * @TableName contamer
 */
@Getter
@Setter
@Entity
@Table(name = "contamer")
public class Contamer implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 账号
     */
    private String loginId;
    
    /**
     * 密码加密
     */
    private String passwold;
    
    /**
     * 昵称
     */
    private String user_name;
    
    /**
     * 性别(0 女 1 男)
     */
    private Integer gender;
    
    /**
     * 头像
     */
    private String portrait;
    
    /**
     * 状态1：停用0：正常
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    
    /**
     * 创建人
     */
    private String create_by;
    
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;
    
    /**
     * 修改人
     */
    private String update_by;
    
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
        Contamer other = (Contamer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                       && (this.getLoginId() == null ? other.getLoginId() == null : this.getLoginId().equals(other.getLoginId()))
                       && (this.getPasswold() == null ? other.getPasswold() == null : this.getPasswold().equals(other.getPasswold()))
                       && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
                       && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
                       && (this.getPortrait() == null ? other.getPortrait() == null : this.getPortrait().equals(other.getPortrait()))
                       && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                       && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
                       && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                       && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginId() == null) ? 0 : getLoginId().hashCode());
        result = prime * result + ((getPasswold() == null) ? 0 : getPasswold().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getPortrait() == null) ? 0 : getPortrait().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginId=").append(loginId);
        sb.append(", passwold=").append(passwold);
        sb.append(", user_name=").append(user_name);
        sb.append(", gender=").append(gender);
        sb.append(", portrait=").append(portrait);
        sb.append(", status=").append(status);
        sb.append(", create_time=").append(create_time);
        sb.append(", create_by=").append(create_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
