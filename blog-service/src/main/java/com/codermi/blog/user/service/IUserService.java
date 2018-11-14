package com.codermi.blog.user.service;

import com.codermi.blog.user.cache.data.dto.UserInfo;

import java.util.List;

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

    /**
     * 更新用户角色，目前仅支持管理员账户
     * @param roleIds
     * @param userId
     */
    void updateUserRole(List<String> roleIds, String userId);


    void test();
}
