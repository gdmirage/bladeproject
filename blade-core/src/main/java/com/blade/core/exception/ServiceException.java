package com.blade.core.exception;

import com.blade.core.enums.ISubCodeEnumInterface;

/**
 * 业务异常
 *
 * @author blade
 * 2019/12/11 15:13
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = -3752750520448447204L;

    private ISubCodeEnumInterface subCodeEnumInterface;

    public ServiceException(ISubCodeEnumInterface subCodeEnumInterface) {
        super(subCodeEnumInterface.getSubCode());
        this.subCodeEnumInterface = subCodeEnumInterface;
    }

    public ISubCodeEnumInterface getSubCodeEnumInterface() {
        return subCodeEnumInterface;
    }
}
