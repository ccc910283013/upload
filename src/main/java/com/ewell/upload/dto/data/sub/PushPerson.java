package com.ewell.upload.dto.data.sub;

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

    @Override
    public String toString() {
        return "PushPerson{" +
                "organCode='" + organCode + '\'' +
                ", organ='" + organ + '\'' +
                ", refType='" + refType + '\'' +
                ", ext='" + ext + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", healthNo='" + healthNo + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", patientNo='" + patientNo + '\'' +
                ", outpatientNo='" + outpatientNo + '\'' +
                '}';
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getHealthNo() {
        return healthNo;
    }

    public void setHealthNo(String healthNo) {
        this.healthNo = healthNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getOutpatientNo() {
        return outpatientNo;
    }

    public void setOutpatientNo(String outpatientNo) {
        this.outpatientNo = outpatientNo;
    }
}
