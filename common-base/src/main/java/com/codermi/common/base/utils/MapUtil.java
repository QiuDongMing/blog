package com.codermi.common.base.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiudm
 * @date 2018/9/12 15:33
 * @desc
 */
public class MapUtil {

    /**
     * javaBean 转 Map
     *
     * @param object 需要转换的javabean
     * @return 转换结果map
     */
    public static Map<String, Object> beanToMap(Object object) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    /**
     * @param map 需要转换的map
     * @param cls 目标javaBean的类对象
     * @return 目标类object
     */
    public static Object mapToBean(Map<String, Object> map, Class cls) throws Exception {
        Object object = cls.newInstance();
        for (String key : map.keySet()) {
            Field temFiles = cls.getDeclaredField(key);
            temFiles.setAccessible(true);
            temFiles.set(object, map.get(key));
        }
        return object;
    }

}
