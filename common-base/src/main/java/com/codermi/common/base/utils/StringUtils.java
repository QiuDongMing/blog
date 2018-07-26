package com.codermi.common.base.utils;

import java.util.UUID;

/**
 * @author qiudm
 * @date 2018/6/29 9:45
 * @desc
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {


    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }

    public static void main(String[] args) {

        String randomId = randomUUID();
        System.out.println("randomId = " + randomId);

    }

}
