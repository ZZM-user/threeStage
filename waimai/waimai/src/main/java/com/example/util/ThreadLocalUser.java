package com.example.util;

import com.example.common.vo.LoginUserVO;

/**
 * @Author： 17602
 * @Date： 2022/8/9 10:31
 * @Desc：
 **/
public class ThreadLocalUser {
    
    public static ThreadLocal<LoginUserVO> loginThreadLocal = new ThreadLocal<>();
}
