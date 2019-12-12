package com.blade.core.enums;

/**
 * 校验枚举
 *
 * @author blade
 * 2019/12/12 17:33
 */
public enum  ValidateResultCodeEnum implements ISubCodeEnumInterface{

    /**
     * 登陆验证提示
     */
    WRONG_ACCOUNT_OR_PASSWORD("wrong account or password", "账号或密码错误"),
    WRONG_CAPTCHA("wrong captcha", "验证码错误"),

    /**
     * 表单校验提示
     */
    INVALID_PHONE_NUMBER("invalid phone number", "无效手机号"),


    ;

    ValidateResultCodeEnum(String subCode, String subMsg) {
        this.subCode = subCode;
        this.subMsg = subMsg;
    }

    /**
     * 详细错误码
     */
    private String subCode;

    /**
     * 详细错误信息
     */
    private String subMsg;

    @Override
    public String getSubCode() {
        return this.subCode;
    }

    @Override
    public String getSubMsg() {
        return this.subMsg;
    }
}
