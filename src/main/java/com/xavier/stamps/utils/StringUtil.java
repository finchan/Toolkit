package com.xavier.stamps.utils;

public class StringUtil {
    public static boolean emptyString(String str) {
        if(str == null||"".equals(str.trim()))
            return true;
        return false;
    }
}
