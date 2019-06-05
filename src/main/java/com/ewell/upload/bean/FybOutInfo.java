package com.ewell.upload.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FybOutInfo extends FybOutTotal{
    /**
     * 省妇幼保健平台返回的孕产妇保健号
     */
    private String healthcareNo;
    /**
     * 省妇幼保健平台返回的孕产妇唯一号
     */
    private String sysId;


}
