package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author： 17602
 * @Date： 2022/8/2 9:49
 * @Desc： 基本分页类
 **/

@Getter
@Setter
public class PageDTO implements Serializable {
    
    private Integer page;
    
    private Integer size;
}
