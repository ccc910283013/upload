package com.ewell.upload.bean;

public class FybOutInfo extends FybOutTotal{
    /**
     * 省妇幼保健平台返回的孕产妇保健号
     */
    private String healthcareNo;
    /**
     * 省妇幼保健平台返回的孕产妇唯一号
     */
    private String sysId;

    @Override
    public String toString() {
        return "FybOutInfo{" +
                "healthcareNo='" + healthcareNo + '\'' +
                ", sysId='" + sysId + '\''  +
                '}';
    }


    public String getHealthcareNo() {
        return healthcareNo;
    }

    public void setHealthcareNo(String healthcareNo) {
        this.healthcareNo = healthcareNo;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }
}
