package com.example.config;

/**
 * @Author： 17602
 * @Date： 2022/8/9 9:16
 * @Desc： 统一全局处理
 **/

import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalMvcExceptionHandler {
    /**
     * hibernate-validator验证全局异常
     *
     * @param exception
     *
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public R bindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        R<Object> build = R.build(AckCode.FAIL);
        build.setData(message);
        return build;
    }
}
