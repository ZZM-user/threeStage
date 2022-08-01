package com.example.common.exception;

import cn.hutool.jwt.JWTException;
import com.example.common.vo.CodeEnum;
import com.example.common.vo.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * springmvc框架处理全局异常处理
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 自定义异常
     *
     * @param e
     */
    @ExceptionHandler(value = ServerException.class)
    public R serverException(ServerException e) {
        log.error(e.getCodeEnum().getMessage());
        return R.failed(e.getCodeEnum());
    }
    
    /**
     * hibernate-validator异常处理
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public R bindException(BindException e) {
        log.error(e);
        String message = e.getFieldErrors().get(0).getDefaultMessage();
        return R.failed(message);
    }
    
    
    @ExceptionHandler(value = JWTException.class)
    public R jWTException(JWTException e) {
        log.error(e);
        return R.failed(CodeEnum.TOKEN_EXPIRED);
    }
}
