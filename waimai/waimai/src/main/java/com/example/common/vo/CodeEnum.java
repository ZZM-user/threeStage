package com.example.common.vo;

import javax.annotation.sql.DataSourceDefinition;

/**
 * 消息枚举
 * 商家功能：  code     1000-1999
 * 商品功能：  code     2000-2999
 * 后台用户:   code     3000-3999
 * 前台用户    code     4000-4999
 * 订单       code     5000-5999
 */
public enum CodeEnum {
    SUCCESS(0,"操作成功"),
    Failed(500,"操作失败"),
    NO_PERMISSION(501,"没有权限进行些项操作"),
    TOKEN_INVALIDATE(400,"未登录，请先登录"),
    TOKEN_EXPIRED(401,"令牌无效请重新登录"),
    EDIT_DATA_NOT_FOUND(402,"要修改的记录不存在"),
    SMS_SEND_REPEAT(403,"验证码已发送过，请稍后再试"),
    SMS_SEND_SUCCESS(404,"验证码发送成功"),
    SMS_SEND_FAILED(405,"验证码发送失败"),
    EXPORT_DATA_TO_BIG(406,"导出的数据量太大，不能导出"),

    DATA_VALIDATED(501,"数据格式无效"),
    ADMIN_ACCOUNT_NOT_FOUND(3000,"账户或密码错误"),
    ADMIN_ACCOUNT_LOCKED(3001,"账户被锁定不能登录"),
    ADMIN_CAPTCHA_EXPIRED(3002,"验证码已过期"),
    ADMIN_CAPTCHA_INCORRECT(3003,"验证码输入错误"),
    ADMIN_LOGIN_FAILED(3004,"用户登录失败"),
    ADMIN_ACCOUNT_EXISTS(0,"该值已被占用"),

    MERCHANDISE_VALIDATE(4000,"只商家才能做操作")

    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
