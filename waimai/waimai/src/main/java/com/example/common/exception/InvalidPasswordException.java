package com.example.common.exception;

import com.example.common.vo.CodeEnum;

/**
 * 密码无效
 */
public class InvalidPasswordException extends ServerException {
    
    public InvalidPasswordException(CodeEnum codeEnum) {
        super(codeEnum);
    }
    
    public InvalidPasswordException() {
        super.setCodeEnum(CodeEnum.ADMIN_ACCOUNT_NOT_FOUND);
    }
    
    public InvalidPasswordException(String message) {
        super(message);
    }
    
    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
