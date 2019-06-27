package com.ewell.upload.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 按驼峰规则建表命名
 */
@Data
@NoArgsConstructor
public class FybInpTotal {
    private String inpNo;
    private String saveTime;
    private String exception;
    private String idNo;
    private String dealStatus;
}
