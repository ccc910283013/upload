package com.ewell.upload.dto.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginToken {
    private String inputOrganCode;
    private String inputOrganName;
    private String userIdcard;
    private String userName;
    private String token;


}
