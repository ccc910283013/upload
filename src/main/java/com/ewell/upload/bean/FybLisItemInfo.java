package com.ewell.upload.bean;

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


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    public String getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(String requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public String getFybResItemName() {
        return fybResItemName;
    }

    public void setFybResItemName(String fybResItemName) {
        this.fybResItemName = fybResItemName;
    }

    public String getFybResItemCode() {
        return fybResItemCode;
    }

    public void setFybResItemCode(String fybResItemCode) {
        this.fybResItemCode = fybResItemCode;
    }

    public String getReportItemName() {
        return reportItemName;
    }

    public void setReportItemName(String reportItemName) {
        this.reportItemName = reportItemName;
    }

    public String getReportItemCode() {
        return reportItemCode;
    }

    public void setReportItemCode(String reportItemCode) {
        this.reportItemCode = reportItemCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
