package com.example.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * @author 14001
 */

@NoArgsConstructor
@AllArgsConstructor
public enum AckCode {
    
    /**
     * 通用提示
     **/
    COMMON_FRE_OPERATION(200, "服务器频繁,请重试"),
    
    /*-------------------编辑资料-------------------*/
    NICK_NAME_TOO_LONG(300, "昵称最多8个字符"),
    ILLEGAL_MONOLOGUE(301, "内心独白违规"),
    ILLEGAL_NICKNAME(302, "昵称涉及敏感词"),
    ILLEGAL_SPECIAL(303, "昵称不能包含特殊符号"),
    
    /*-----Basic-----*/
    SUCCESS(0, "ok"),
    VALUE_IS_USED(1, "该值已被使用"),
    FAIL(500, "fail"),
    SYSTEM_PARAM_FAIL(400, "参数错误"),
    SYSTEM_TOKEN_FAIL(401, "会话无效，请重新登录"),
    SYSTEM_SIGNATURE_FAIL(402, "签名错误"),
    SYSTEM_DATA_FAIL(500, "系统数据异常"),
    SYSTEM_SERVER_BUSY(501, "服务器繁忙"),
    SYSTEM_SERVER_MAINTAINING(503, "系统维护中"),
    NOT_FOUND_DATA(504, "访问不到数据"),
    
    
    /*-------------------登录-------------------*/
    LOGIN_URL_ERROR(600, "登录回调路径错误"),
    JPUSH_LOGIN_ERROR(601, "极光登录失败，请重新登录"),
    ALIYUN_LOGIN_ERROR(602, "阿里云登录失败，请重新登录"),
    GENDER_TYPE_ERROR(603, "性别类型错误"),
    DEVICE_NOT_EXIST(604, "设备不存在"),
    DEVICE_REGISTER_TOO_MUCH(605, "一个设备只能注册10个账号"),
    //    LOGIN_BANNED(606, "账号ID：*已被锁定，请联系客服。\n客服QQ：80056867"),
    LOGIN_BANNED(606, "您的账号因违规操作现已被%s，如有疑问，请联系客服！客服QQ：80056867"),
    WITHDRAW_BANNED(607, "提现受限，请联系客服"),
    LOG_OFF(608, "您账号已经注销"),
    DEVICE_BANNED(609, "帐号异常：*已被锁定，请联系客服。\n客服QQ：80056867"),
    APPLE_EXPIRED(610, "苹果token过期"),
    APPLE_ILLEGAL(611, "苹果token非法"),
    APPLE_FAIL(612, "苹果token验证失败"),
    INVITATION_CODE_ERROR(613, "邀请码不正确"),
    TOKEN_EXPIRED(614, "令牌过期"),
    
    
    /*-------------------短信验证-------------------*/
    SMS_SEND_ERROR(700, "验证码发送失败"),
    SMS_SYSTEM_ERROR(701, "验证码发送系统异常"),
    SMS_CODE_WRONG(702, "验证码错误"),
    SMS_CODE_OR_MOBILE_WRONG(702, "验证码或者旧手机号错误"),
    SMS_DEVICE_WRONG(703, "设备型号不匹配"),
    SMS_CODE_OVERTIME(704, "验证码超时"),
    SMS_CODE_STATUS_USED(705, "验证码已使用"),
    SMS_SEND_OVERTIMES(706, "今日发送次数已用完"),
    SMS_SEND_TYPE_WRONG(707, "发送验证码类型错误"),
    SMS_SEND_COLD_TIME(708, "两次验证码发送间隔不能小于1分钟"),
    /**
     * 身份证验证性别
     */
    IDCARD_GENDER_ERROR(708, "身份证性别不一致"),
    SMS_NOT_SAVE(709, "验证码发送中"),
    
    
    /*--------------------文件-----------------*/
    FILE_NOT_EXIST(800, "文件不存在"),
    FILE_UPLOAD_OSS_TOKEN_ERROR(801, "获取上传令牌失败"),
    UPLOAD_TYPE_ERROR(802, "上传文件类型有误"),
    UPLOAD_FILE_NAME_ERROR(803, "上传文件名称有误"),
    UPLOAD_FILE_CORRUPTED(804, "文件损坏，请重新上传"),
    UPLOAD_PHOTO_LIMIT(805, "照片最多上传5张"),
    UPLOAD_VOICE_LIMIT(806, "语音时长太短"),
    FILE_NOT_EMPTY(807, "文件不能为空"),
    FILE_NOT_SIZE(808, "图片大小不能超过2MB"),
    
    /*-----User (StartCode: 1000)--------------*/
    USER_PASSWORD_ERROR(1000, "密码错误"),
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_HAS_ACCOUNT(1016, "该账户已存在"),
    USER_PAY_ACCOUNT_IS_BIND(1017, "支付账户已绑定"),
    USER_LOGIN_ACCOUNT_IS_BIND(1018, "该登录账户已绑定"),
    USER_ACCOUNT_AUTH_EXCEPTION(1019, "认证查询异常，接口调用失败"),
    USER_PAY_IS_FAIL(1020, "支付失败"),
    USER_PAY_IS_EXCEPTION(1021, "支付异常"),
    USER_WITHDRAW_LIMIT(1022, "提现受限"),
    USER_ACCOUNT_IS_NOT_BIND(1023, "支付账户未绑定"),
    USER_ACCOUNT_IS_NOT_FOUND(1024, "未找到支付账户"),
    USER_PARAM_IS_NOT_NULL(1025, "参数不能为空"),
    USER_ACCOUNT_UPDATE_COUNT_LIMIT(1026, "账户修改次数当月达到上限"),
    USER_FIRST_CHARGE_COUNT_LIMIT(1027, "首充已达上限"),
    USER_WITHDRAW_STATUS_ERROR(1028, "只有未审核的才能进行操作哟"),
    USER_VIOLATION(1034, "用户涉嫌违规"),
    USER_IS_NOT_MAN(1035, "抱歉您不是男用户"),
    USER_LOG_OFF(1036, "用户已注销"),
    USER_FACE_VERIFY(1037, "您已真人认证"),
    USER_BANNED(1038, "该用户已被封禁"),
    FAMILY_SHARE_LINK_IS_EXPIRE(1114, "家族分享链接已过期"),
    LOGIN_ACCOUNT_PASSWORD_ERROR(1115, "账号或密码错误"),
    LOGIN_PASSWORD_ERROR(1116, "密码错误"),
    LOGIN_PASSWORD_INVALID(1117, "密码不正确"),
    SET_PASSWORD_INVALID(1118, "两次输入密码不一致"),
    OLD_PASSWORD_NOTNULL(1119, "原密码不能为空"),
    OLD_PASSWORD_INVALID(1120, "原密码不正确"),
    OLD_PASSWORD_ERROR(1121, "原密码错误"),
    PHONE_NUMBER_SET_ACCOUNT_INVALID(1122, "该手机号码已经被其他用户绑定"),
    PLEASE_BIND_PHONE_NUMBER(1123, "请先绑定手机号码"),
    PHONE_NUMBER_NOTNULL(1124, "手机号码不能为空"),
    PASSWORD_VERIFY_FAILED(1125, "密码必须是6~16位的数字、字母组合"),
    BUY_LIMIT(1126, "购买受限"),
    
    // 商家
    ENTERPRISE_NOT_VALIDATED(2000, "商家信息未审核，不允许登录");
    
    
    public Integer code;
    
    public String msg;
    
    /**
     * 获取所有回复码
     *
     * @return
     */
    public static LinkedHashMap<Integer, String> getArrayMessage() {
        LinkedHashMap<Integer, String> responseMessages = new LinkedHashMap<>();
        for (AckCode statusEnum : AckCode.values()) {
            responseMessages.put(statusEnum.code, statusEnum.msg);
        }
        return responseMessages;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
