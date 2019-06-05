package com.ewell.upload.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@ToString
public class BaseResponse<T> {
    @NotNull
    private String result;
    private String message;
    private String total;
    private T data;

}
