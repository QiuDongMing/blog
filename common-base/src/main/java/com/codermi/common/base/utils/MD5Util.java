package com.codermi.common.base.utils;

import org.apache.commons.codec.digest.DigestUtils;



/**
 * @author qiudm
 * @date 2018/7/27 4:27
 * @desc
 */
public class MD5Util {


//    /**
//     * 利用MD5进行加密
//     */
//    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//
//        //确定计算方法
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        BASE64Encoder base64en = new BASE64Encoder();
//        //加密后的字符串
//        return  base64en.encode(md5.digest(str.getBytes("utf-8")));
//    }

    /**
     * 获取密码的密文
     * @param str:要加密的字符
     * @return saltKey：加密的盐值
     */
    public static String md5Hex(String str, String saltKey) {
      return DigestUtils.md5Hex(str + saltKey);
    }


}
