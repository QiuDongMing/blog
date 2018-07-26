package com.coderme.blog.user.service;

import com.coderme.blog.user.cache.data.dto.UserInfo;

/**
 * @author qiudm
 * @date 2018/6/28 10:31
 * @desc
 */
public interface IUserService {

    UserInfo getBaseUserInfo(String userId);

    void setBaseUserInfo(UserInfo userInfo);


}
