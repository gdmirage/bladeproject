package com.blade.core.exception;

import com.blade.core.enums.ICommonResultCodeEnumInterface;

/**
 * 系统异常
 *
 * @author blade
 * 2019/12/11 15:13
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = -5650826544444834624L;

    private ICommonResultCodeEnumInterface commonResultCodeEnumInterface;

    public SystemException(ICommonResultCodeEnumInterface commonResultCodeEnumInterface) {
        super(commonResultCodeEnumInterface.getMsg());
        this.commonResultCodeEnumInterface = commonResultCodeEnumInterface;
    }

    public ICommonResultCodeEnumInterface getCommonResultCodeEnumInterface() {
        return commonResultCodeEnumInterface;
    }
}
