package com.ewell.upload.bean;

import lombok.Data;

@Data
public class FybLisItemInfo {
    private String patientId;
    private String visitId;
    private String testNo;
    private String requestedDateTime;
    private String fybResItemName;
    private String fybResItemCode;
    private String reportItemName;
    private String reportItemCode;
    private String result;


}
