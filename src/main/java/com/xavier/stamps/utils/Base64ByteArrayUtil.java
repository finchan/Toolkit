package com.xavier.stamps.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64ByteArrayUtil {
    public static byte[] base64String2ByteFun(String base64Str){
        return Base64.decodeBase64(base64Str);
    }
    public static String byte2Base64StringFun(byte[] b){
        return Base64.encodeBase64String(b);
    }
}
