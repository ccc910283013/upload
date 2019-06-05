package com.ewell.upload.dto.data.sub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PushPerson {
    private String organCode;
    private String organ;
    private String refType;
    private String ext;
    private String checkDate;
    private String healthNo;
    private String name;
    private String idcard;
    private String birthday;
    private String sex;
    private String cardType;
    private String cardNo;
    private String patientNo;
    private String outpatientNo;
    private String tbKey;
    private String dbKey;
}
