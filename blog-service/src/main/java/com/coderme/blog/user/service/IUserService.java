package com.coderme.blog.user.service;

import com.coderme.blog.user.cache.data.dto.BaseUserInfo;

/**
 * @author qiudm
 * @date 2018/6/28 10:31
 * @desc
 */
public interface IUserService {

    BaseUserInfo getBaseUserInfo(String userId);

    void setBaseUserInfo(BaseUserInfo baseUserInfo);


}
