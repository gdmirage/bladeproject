package com.blade.core.enums;

/**
 * 业务异常的接口
 *
 * @author blade
 * 2019/12/11 15:55
 */
public interface ISubCodeEnumInterface {

    /**
     * 获取详细状态码
     * @return sub code
     */
    String getSubCode();

    /**
     * 获取详细信息
     * @return sub msg
     */
    String getSubMsg();
}
