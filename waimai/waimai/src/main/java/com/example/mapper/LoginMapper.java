package com.example.mapper;

import com.example.common.vo.LoginUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/15 14:16
 * @Desc： 自定义接口
 **/
@Mapper
public interface LoginMapper {
    
    /**
     * 根据用户名查询
     *
     * @param account
     *
     * @return
     */
    List<LoginUserVO> login(String account);
}
