package com.ewell.upload.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FybOutTotal {
    /**
     * 门诊挂号主键
     */
    private String outpCheckNo;
    /**
     * 患者ID
     */
    private String patientId;
    /**
     * 挂号日期YYYY-MM-DD
     */
    private String regTime;
    /**
     * 初诊、复诊标志：写初次检查病历为0，写产前复查病历为1
     */
    private String status;
    /**
     * 身份证号
     */
    private String idNo;

}
