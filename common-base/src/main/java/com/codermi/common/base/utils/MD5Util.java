package com.codermi.common.base.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author qiudm
 * @date 2018/7/27 4:27
 * @desc
 */
public class MD5Util {
    /**
     * 利用MD5进行加密
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        return  base64en.encode(md5.digest(str.getBytes("utf-8")));
    }


}
