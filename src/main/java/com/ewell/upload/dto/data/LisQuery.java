package com.ewell.upload.dto.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

}
