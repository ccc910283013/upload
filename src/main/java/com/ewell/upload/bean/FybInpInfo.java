package com.ewell.upload.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 按驼峰规则建表命名
 */
@Data
@NoArgsConstructor
public class FybInpInfo {
    private String inpNo;
    private String saveTime;
    private String healthcareNo;
    private String sysId;
    private String idNo;
    private String exception;
    private String dealStatus;
}
