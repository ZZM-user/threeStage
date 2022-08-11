package com.example.entity;

import com.example.common.validtor.EditValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家信息表
 *
 * @TableName enterprise
 */
@Getter
@Setter
@Entity
@Table(name = "enterprise")
public class Enterprise implements Serializable {
    /**
     *
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 店名
     */
    @NotNull(message = "商家名称不能为空", groups = {AddressBook.class, EditValidator.class})
    private String name;
    
    /**
     * 封面
     */
    @NotNull(message = "商家封面不能为空", groups = {AddressBook.class, EditValidator.class})
    private String album;
    
    /**
     * 地址
     */
    @NotNull(message = "商家地址不能为空", groups = {AddressBook.class, EditValidator.class})
    private String address;
    
    /**
     * 经度
     */
    @NotNull(message = "商家位置不能为空", groups = {AddressBook.class, EditValidator.class})
    private BigDecimal longitude;
    
    /**
     * 纬度
     */
    @NotNull(message = "商家位置不能为空", groups = {AddressBook.class, EditValidator.class})
    private BigDecimal latitude;
    
    /**
     * 联系电话
     */
    @NotNull(message = "商家电话不能为空", groups = {AddressBook.class, EditValidator.class})
    @Pattern(regexp = "^0?(13|15|17|18|14)[0-9]{9}$", message = "电话格式有误！", groups = {AddressBook.class, EditValidator.class})
    private String phone;
    
    /**
     * 登录密码(加密)
     */
    @NotNull(message = "商家密码不能为空", groups = {AddressBook.class})
    @Length(min = 6, max = 20, message = "密码长度需要在6-20字符之间")
    private String login_pwd;
    
    /**
     * 状态（1停用，0正常  2未审核）
     */

    @NotNull(message = "商家状态不能为空", groups = {AddressBook.class, EditValidator.class})
    private Integer status;
    
    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date audit_time;
    
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
        Enterprise other = (Enterprise) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                       && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                       && (this.getAlbum() == null ? other.getAlbum() == null : this.getAlbum().equals(other.getAlbum()))
                       && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                       && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                       && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
                       && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                       && (this.getLogin_pwd() == null ? other.getLogin_pwd() == null : this.getLogin_pwd().equals(other.getLogin_pwd()))
                       && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                       && (this.getAudit_time() == null ? other.getAudit_time() == null : this.getAudit_time().equals(other.getAudit_time()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAlbum() == null) ? 0 : getAlbum().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getLogin_pwd() == null) ? 0 : getLogin_pwd().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAudit_time() == null) ? 0 : getAudit_time().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", album=").append(album);
        sb.append(", address=").append(address);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", phone=").append(phone);
        sb.append(", login_pwd=").append(login_pwd);
        sb.append(", status=").append(status);
        sb.append(", audit_time=").append(audit_time);
        sb.append(", create_time=").append(create_time);
        sb.append(", create_by=").append(create_by);
        sb.append(", update_time=").append(update_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
