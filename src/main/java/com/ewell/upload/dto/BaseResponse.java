package com.ewell.upload.dto;

import javax.validation.constraints.NotNull;

public class BaseResponse<T> {
    @NotNull
    private String result;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
