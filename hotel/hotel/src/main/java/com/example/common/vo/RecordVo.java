package com.example.common.vo;

import com.example.entity.Record;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/19 12:23
 * @Desc：
 **/
@Getter
@Setter
public class RecordVo extends Record {
    String employee;
    String room;
}
