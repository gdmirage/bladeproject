package com.blade.manager.system.common;

/**
 * @author blade
 * 2019/9/19 17:39
 */
public class ResponseResult<T> {

    private int status;

    private String message;

    private T data;

    public static <T> ResponseResult<T> ok(T data) {
        return ok(200, "success", data);
    }

    public static <T> ResponseResult<T> ok(int status, String message, T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setStatus(status);
        responseResult.setMessage(message);
        responseResult.setData(data);

        return responseResult;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
