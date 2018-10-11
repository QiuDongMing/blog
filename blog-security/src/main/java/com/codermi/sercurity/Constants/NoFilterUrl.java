package com.codermi.sercurity.Constants;


import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


/**
 * @author qiudm
 * @date 2018/9/19 19:11
 * @desc
 */
public class NoFilterUrl {

    /**
     * 无需过滤的URL匹配
     */
    public static final String[] URL_NO_FILTER_ARRAY = {
            "/user/login",
            "/user/register",
            "/**/open/**"
    };


    public static void main(String[] args) {
        String url = "/user/open/test";
        PathMatcher pathMatcher = new AntPathMatcher();
        boolean match = pathMatcher.match("/**/open/**", url);
        System.out.println("match = " + match);

    }


}
