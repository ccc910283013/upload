package com.ewell.upload.dto.data;

public class WomanMain {
    private String sysId;
    private String healthNo;


    @Override
    public String toString() {
        return "WomanMain{" +
                "sysId='" + sysId + '\'' +
                ", healthNo='" + healthNo + '\'' +
                '}';
    }


    public String getSysId() {

        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getHealthNo() {
        return healthNo;
    }

    public void setHealthNo(String healthNo) {
        this.healthNo = healthNo;
    }
}
