package com.ewell.upload.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class BaseResponse<T> {
    private String result;
    private String message;
    private String total;
    private T data;
}
