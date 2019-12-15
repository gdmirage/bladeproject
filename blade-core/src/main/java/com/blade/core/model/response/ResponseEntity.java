package com.blade.core.model.response;

import com.blade.core.enums.CommonResultCodeEnum;
import com.blade.core.enums.ICommonResultCodeEnumInterface;
import com.blade.core.enums.ISubCodeEnumInterface;
import com.blade.core.exception.ServiceException;
import com.blade.core.exception.SystemException;

/**
 * @author blade
 * 2019/12/11 15:34
 */
public class ResponseEntity {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 详细的返回码
     */
    private String subCode;

    /**
     * 详细的返回信息
     */
    private String subMsg;

    /**
     * 成功与否的标识
     */
    private Boolean success;

    public ResponseEntity(){}

    public ResponseEntity(ICommonResultCodeEnumInterface commonResultCodeEnumInterface,
                          ISubCodeEnumInterface subCodeEnumInterface, Boolean success) {
        if (null == commonResultCodeEnumInterface) {
            throw new SystemException(CommonResultCodeEnum.EXCEPTION);
        }

        this.code = commonResultCodeEnumInterface.getCode();
        this.msg = commonResultCodeEnumInterface.getMsg();

        if (null != subCodeEnumInterface) {
            this.subCode = subCodeEnumInterface.getSubCode();
            this.subMsg = subCodeEnumInterface.getSubMsg();
        }

        this.success = success;
    }

    public ResponseEntity(int code, String msg, String subCode, String subMsg, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.success = success;
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(CommonResultCodeEnum.SUCCESS, null, true);
    }

    public static ResponseEntity exception(ServiceException e) {
        return new ResponseEntity(CommonResultCodeEnum.EXCEPTION, e.getSubCodeEnumInterface(), false);
    }

    public static ResponseEntity exception(SystemException systemException) {
        return new ResponseEntity(systemException.getCommonResultCodeEnumInterface(), null, false);
    }

    public static ResponseEntity exception() {
        return new ResponseEntity(CommonResultCodeEnum.EXCEPTION, null, false);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public Boolean getSuccess() {
        return success;
    }
}
