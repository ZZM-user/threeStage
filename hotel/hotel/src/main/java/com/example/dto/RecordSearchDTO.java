package com.example.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/19 12:31
 * @Desc：
 **/
@Setter
@Getter
public class RecordSearchDTO extends PageDTO {
    String employee;
    String room;
}
