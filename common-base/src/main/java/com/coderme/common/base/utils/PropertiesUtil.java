package com.coderme.common.base.utils;

import org.springframework.core.env.Environment;

/**
 * @author qiudm
 * @date 2018/6/28 18:09
 * @desc
 */
public class PropertiesUtil {

    public static Environment env;

    public static String getContextProperty(String name) {
        return env.getProperty(name);
    }


}
