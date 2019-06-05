package com.ewell.upload.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseRequest<T> {
    private String source;
    private String remark;
    private String operate;
    private T data;
}
