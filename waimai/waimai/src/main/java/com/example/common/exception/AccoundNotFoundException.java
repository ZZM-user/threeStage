package com.example.common.exception;

import com.example.common.vo.CodeEnum;

/**
 * 账户找不到
 */
public class AccoundNotFoundException extends ServerException {
    
    public AccoundNotFoundException(CodeEnum codeEnum) {
        super(codeEnum);
    }
    
    public AccoundNotFoundException() {
        super.setCodeEnum(CodeEnum.ADMIN_ACCOUNT_NOT_FOUND);
    }
    
    public AccoundNotFoundException(String message) {
        super(message);
    }
    
    public AccoundNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
