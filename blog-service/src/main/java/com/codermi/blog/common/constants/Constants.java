package com.codermi.blog.common.constants;

/**
 * @author qiudm
 * @date 2018/7/30 14:30
 * @desc
 */
public class Constants {

    public static final String PASSWORD_SALT = "codemi@2018.";

    /**
     * 单位秒
     */
    public interface Expire {
        Long MINUTE1 = 60L;
        Long MINUTE3 = 180L;
        Long HOUR1 = 3600L;
        Long DAY1 = 86400L;
        Long DAY7 = 604800L;
        Long MONTH1 = 86400*30L;
        Long MONTH3 = 86400*90L;
        Long HOUR12 = 43200L;
    }

    public interface CacheKeyPre {
        String TOKEN_USER_LOGIN = "token_user_login";
        String USERID_TOKEN = "userId_token";
        String USER_INFO = "user";
        String WX_PUB_ACCESS_TOKEN = "wx_pub_access_token";
    }





}
