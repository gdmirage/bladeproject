package com.blade.core.enums;

/**
 * @author blade
 * 2019/12/11 15:24
 */
public interface ICommonResultCodeEnumInterface {

    /**
     * 获取状态码
     * @return status code
     */
    int getCode();

    /**
     * 获取状态信息
     * @return status msg
     */
    String getMsg();
}
