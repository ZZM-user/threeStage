package com.example.common.aspect;

import com.example.common.WaimaiException;
import com.example.common.enums.AckCode;
import com.example.util.ThreadLocalUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author： 17602
 * @Date： 2022/8/18 11:51
 * @Desc： 实现功能
 **/
@Component
@Aspect
public class AdminAccessAspect {
    
    @Pointcut(value = "@annotation(com.example.common.annon.AdminAccess)")
    private void scanAdminAccess() {
    
    }
    
    /**
     * 动态代理-前置增强
     */
    @Before(value = "scanAdminAccess()")
    public void beforeValidatorAdmin() {
        System.out.println("发现请求类型：" + ThreadLocalUser.loginThreadLocal.get());
        if (ThreadLocalUser.loginThreadLocal.get().getLoginType() != 1) {
            throw new WaimaiException(AckCode.NOT_PERMISSION_TO_ACCESS);
        }
    }
}
