package com.example.common.exception;

import com.example.common.vo.CodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 */
@Getter
@Setter
public class ServerException extends RuntimeException {
    
    protected CodeEnum codeEnum;
    
    public ServerException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }
    
    public ServerException() {
    }
    
    public ServerException(String message) {
        super(message);
    }
    
    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
