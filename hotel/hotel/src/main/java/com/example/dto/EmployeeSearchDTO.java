package com.example.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/19 10:51
 * @Desc：
 **/
@Getter
@Setter
public class EmployeeSearchDTO extends PageDTO {
    String name;
    Integer hasRoom;
}
