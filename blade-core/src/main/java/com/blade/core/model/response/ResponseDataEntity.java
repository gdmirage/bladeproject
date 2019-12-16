package com.blade.core.model.response;

import com.blade.core.enums.CommonResultCodeEnum;
import com.blade.core.enums.ICommonResultCodeEnumInterface;
import com.blade.core.enums.ISubCodeEnumInterface;
import com.blade.core.exception.SystemException;

/**
 * 返回的带数据的实体
 *
 * @param <T> 泛型
 * @author Blade
 * 2019/12/16 11:39
 */
public class ResponseDataEntity<T> extends ResponseEntity {

    private T data;

    public ResponseDataEntity(ICommonResultCodeEnumInterface commonResultCodeEnumInterface,
                          ISubCodeEnumInterface subCodeEnumInterface, Boolean success, T data) {
        if (null == commonResultCodeEnumInterface) {
            throw new SystemException(CommonResultCodeEnum.EXCEPTION);
        }

        super.code = commonResultCodeEnumInterface.getCode();
        super.msg = commonResultCodeEnumInterface.getMsg();

        if (null != subCodeEnumInterface) {
            super.subCode = subCodeEnumInterface.getSubCode();
            super.subMsg = subCodeEnumInterface.getSubMsg();
        }

        this.data = data;

        super.success = success;
    }

    public static <T> ResponseDataEntity<T> ok(T data) {
        ResponseDataEntity<T> responseDataEntity = new ResponseDataEntity(CommonResultCodeEnum.SUCCESS, null, true, data);
        responseDataEntity.setData(data);

        return responseDataEntity;
    }

    public static ResponseDataEntity ok() {
        return new ResponseDataEntity(CommonResultCodeEnum.SUCCESS, null, true, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
