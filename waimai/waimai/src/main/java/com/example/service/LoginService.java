package com.example.service;

import com.example.common.vo.LoginUserVO;
import com.example.dto.LoginDTO;

/**
 * @Author： 17602
 * @Date： 2022/8/15 15:12
 * @Desc：
 **/
public interface LoginService {
    /**
     * 根据用户名查询
     *
     * @param loginDTO
     *
     * @return
     */
    LoginUserVO login(LoginDTO loginDTO);
}
