package com.codermi.blog.user.utils;

/**
 * @author qiudm
 * @date 2018/9/6 17:19
 * @desc
 */
public class KeyBuilder {


    public static String getCacheKey(String keyPre, String keyAfter) {
        return String.format("%1$s:%2$s", keyPre, keyAfter);
    }



}
