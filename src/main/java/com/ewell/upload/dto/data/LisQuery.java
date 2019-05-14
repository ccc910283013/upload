package com.ewell.upload.dto.data;

public class LisQuery {
    /**
     *起始日期,格式yyyy-MM-dd
     */
    private String checkDate1;
    /**
     *终止日期,格式yyyy-MM-dd
     */
    private String checkDate2;
    /**
     *机构代码,字符类型,不可空,由妇幼提供或调login接口得到
     */
    private String inputOrganCode;

    @Override
    public String toString() {
        return "LisQuery{" +
                "checkDate1='" + checkDate1 + '\'' +
                ", checkDate2='" + checkDate2 + '\'' +
                ", inputOrganCode='" + inputOrganCode + '\'' +
                '}';
    }

    public String getCheckDate1() {
        return checkDate1;
    }

    public void setCheckDate1(String checkDate1) {
        this.checkDate1 = checkDate1;
    }

    public String getCheckDate2() {
        return checkDate2;
    }

    public void setCheckDate2(String checkDate2) {
        this.checkDate2 = checkDate2;
    }

    public String getInputOrganCode() {
        return inputOrganCode;
    }

    public void setInputOrganCode(String inputOrganCode) {
        this.inputOrganCode = inputOrganCode;
    }
}
