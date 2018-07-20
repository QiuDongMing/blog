package com.coderme.blog.user.cache.data.dto;

import java.io.Serializable;

/**
 * @author qiudm
 * @date 2018/7/20 11:42
 * @desc
 */
public class BaseUserInfo implements Serializable {

    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
