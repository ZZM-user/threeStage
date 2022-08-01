package com.example.common.exception;

import com.example.common.vo.CodeEnum;

/**
 * 账户锁定
 */
public class AccountLockedException extends ServerException {
    public AccountLockedException(CodeEnum codeEnum) {
        super(codeEnum);
    }
    
    public AccountLockedException() {
        super.setCodeEnum(CodeEnum.ADMIN_ACCOUNT_LOCKED);
    }
    
    public AccountLockedException(String message) {
        super(message);
    }
    
    public AccountLockedException(String message, Throwable cause) {
        super(message, cause);
    }
}
