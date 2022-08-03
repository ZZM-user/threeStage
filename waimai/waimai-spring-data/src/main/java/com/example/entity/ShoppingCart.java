package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车
 *
 * @TableName shopping_cart
 */
@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 图片
     */
    private String image;
    
    /**
     * 主键
     */
    private Long user_id;
    
    /**
     * 菜品id
     */
    private Long dish_id;
    
    /**
     * 套餐id
     */
    private Long setmeal_id;
    
    /**
     * 口味
     */
    private String dish_flavor;
    
    /**
     * 数量
     */
    private Integer number;
    
    /**
     * 金额
     */
    private BigDecimal amount;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    
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
        ShoppingCart other = (ShoppingCart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                       && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                       && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
                       && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
                       && (this.getDish_id() == null ? other.getDish_id() == null : this.getDish_id().equals(other.getDish_id()))
                       && (this.getSetmeal_id() == null ? other.getSetmeal_id() == null : this.getSetmeal_id().equals(other.getSetmeal_id()))
                       && (this.getDish_flavor() == null ? other.getDish_flavor() == null : this.getDish_flavor().equals(other.getDish_flavor()))
                       && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
                       && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                       && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getDish_id() == null) ? 0 : getDish_id().hashCode());
        result = prime * result + ((getSetmeal_id() == null) ? 0 : getSetmeal_id().hashCode());
        result = prime * result + ((getDish_flavor() == null) ? 0 : getDish_flavor().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
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
        sb.append(", image=").append(image);
        sb.append(", user_id=").append(user_id);
        sb.append(", dish_id=").append(dish_id);
        sb.append(", setmeal_id=").append(setmeal_id);
        sb.append(", dish_flavor=").append(dish_flavor);
        sb.append(", number=").append(number);
        sb.append(", amount=").append(amount);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
