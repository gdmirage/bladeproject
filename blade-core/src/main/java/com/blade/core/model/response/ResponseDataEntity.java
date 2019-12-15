package com.blade.core.model.response;

public class ResponseDataEntity<T> extends ResponseEntity {

    private T data;

    public static <T> ResponseDataEntity<T> ok(T data) {
        ResponseDataEntity<T> responseDataEntity = new ResponseDataEntity<T>();
        responseDataEntity.setData(data);
        return responseDataEntity;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
