package com.ewell.upload.dto.data;

public class LoginToken {
    private String inputOrganCode;
    private String inputOrganName;
    private String userIdcard;
    private String userName;
    private String token;

    public LoginToken(){ }

    @Override
    public String toString() {
        return "LoginToken{" +
                "inputOrganCode='" + inputOrganCode + '\'' +
                ", inputOrganName='" + inputOrganName + '\'' +
                ", userIdcard='" + userIdcard + '\'' +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getInputOrganCode() {
        return inputOrganCode;
    }

    public void setInputOrganCode(String inputOrganCode) {
        this.inputOrganCode = inputOrganCode;
    }

    public String getInputOrganName() {
        return inputOrganName;
    }

    public void setInputOrganName(String inputOrganName) {
        this.inputOrganName = inputOrganName;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
