package com.ewell.upload.util;

import com.ewell.upload.bean.FybInpInfo;

public class StringUtils {
    public static int L500 = 500;
    public static int L1000 = 1000;
    public static int L2000 = 2000;

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }



    public static String getSubStr(String str1,int length){
        if (isEmpty(str1)){
            return "";
        }
        return str1.substring(0,str1.length()>length?length:str1.length());
    }
}
