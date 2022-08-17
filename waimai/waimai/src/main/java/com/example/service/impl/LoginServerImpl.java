package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.WaimaiException;
import com.example.common.enums.AckCode;
import com.example.common.vo.LoginUserVO;
import com.example.dto.LoginDTO;
import com.example.mapper.LoginMapper;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/15 15:12
 * @Desc：
 **/
@Service
public class LoginServerImpl implements LoginService {
    @Autowired
    LoginMapper mapper;
    
    /**
     * 根据用户名查询
     *
     * @param LoginDTO
     *
     * @return
     */
    @Override
    public LoginUserVO login(LoginDTO loginDTO) {
        List<LoginUserVO> loginList = mapper.login(loginDTO.getAccount());
        LoginUserVO currentUser = null;
        // 如果啥都没查到，直接返回  用户不存在
        if (loginList.size() == 0) {
            throw new WaimaiException(AckCode.USER_NOT_FOUND);
        }
        // 通过密码匹配，取出用户
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 密码校验、状态检查
        for (LoginUserVO loginUserVO : loginList) {
            if (encoder.matches(loginDTO.getLoginPwd(), loginUserVO.getLoginPwd())) {
                // 如果状态异常，不可登录
                if (loginUserVO.getStatus() == 2) {
                    throw new WaimaiException(AckCode.ENTERPRISE_NOT_VALIDATED);
                } else if (loginUserVO.getStatus() == 1) {
                    throw new WaimaiException(AckCode.DEVICE_BANNED);
                } else {
                    currentUser = loginUserVO;
                }
            }
        }
        if (ObjectUtil.isNull(currentUser)) {
            throw new WaimaiException(AckCode.LOGIN_ACCOUNT_PASSWORD_ERROR);
        }
        return currentUser;
    }
    
}
