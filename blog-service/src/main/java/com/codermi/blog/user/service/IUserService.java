package com.codermi.blog.user.service;

import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.data.request.RegisterRequest;

/**
 * @author qiudm
 * @date 2018/6/28 10:31
 * @desc
 */
public interface IUserService {

    /**
     * 获取基础用户信息
     * @param userId
     * @return
     */
    UserInfo getUserInfo(String userId);

    /**
     * 缓存用户信息到redis
     * @param userInfo
     */
    void cacheUserInfo(UserInfo userInfo);



}
