package com.blade.core.enums;

/**
 * 接口功能返回码设计
 *
 * @author blade
 * 2019/12/11 15:05
 */
public enum CommonResultCodeEnum implements ICommonResultCodeEnumInterface{
    /**
     * 请求响应成功
     */
    SUCCESS(10000, "操作成功"),

    /**
     * 操作失败
     */
    FAIL(20000, "操作错误"),

    /**
     * 系统出现异常
     */
    EXCEPTION(30000, "异常"),
    ;

    private int code;
    private String msg;

    CommonResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
