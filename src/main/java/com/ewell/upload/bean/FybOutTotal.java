package com.ewell.upload.bean;

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

    @Override
    public String toString() {
        return "FybOutTotal{" +
                "outpCheckNo='" + outpCheckNo + '\'' +
                ", patientId='" + patientId + '\'' +
                ", regTime='" + regTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getOutpCheckNo() {
        return outpCheckNo;
    }

    public void setOutpCheckNo(String outpCheckNo) {
        this.outpCheckNo = outpCheckNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
