package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName room
 */
@TableName(value = "room")
@Data
public class Room implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 房间
     */
    @TableField(value = "room")
    private String room;
    
    /**
     * 可入住人数
     */
    @TableField(value = "total_people")
    private Integer totalPeople;
    
    /**
     * 住一天的价格
     */
    @TableField(value = "price_of_day")
    private BigDecimal priceOfDay;
    
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
        Room other = (Room) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                       && (this.getRoom() == null ? other.getRoom() == null : this.getRoom().equals(other.getRoom()))
                       && (this.getTotalPeople() == null ? other.getTotalPeople() == null : this.getTotalPeople().equals(other.getTotalPeople()))
                       && (this.getPriceOfDay() == null ? other.getPriceOfDay() == null : this.getPriceOfDay().equals(other.getPriceOfDay()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoom() == null) ? 0 : getRoom().hashCode());
        result = prime * result + ((getTotalPeople() == null) ? 0 : getTotalPeople().hashCode());
        result = prime * result + ((getPriceOfDay() == null) ? 0 : getPriceOfDay().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", room=").append(room);
        sb.append(", totalPeople=").append(totalPeople);
        sb.append(", priceOfDay=").append(priceOfDay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
