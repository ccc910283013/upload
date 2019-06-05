package com.ewell.upload.bean;

import lombok.Data;

@Data
public class FybRisItemInfo{
    private String patientId;
    private String visitId;
    private String examNo;
    private String requestedDateTime;
    private String fybResItemName;
    private String fybResItemCode;
    private String result;
}
