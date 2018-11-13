package com.codermi.blog.auth.service;

import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.data.po.Permission;
import com.codermi.blog.user.data.request.RegisterRequest;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 14:22
 * @desc
 */
public interface ISecurityService {

    /**
     * 根据账号密码登陆
     * @param username
     * @param password
     * @return
     */
    AccessToken loginByUserNamePassword(String username, String password, Integer userType);

    /**
     * 注册
     * @param registerRequest
     */
    void register(RegisterRequest registerRequest);

    /**
     * 获取登录的token
     * @param token
     * @return
     */
    AccessToken getAccessToken(String token);

    /**
     * 获取用户的权限列表
     * @param userId
     * @return
     */
    List<Permission> getUserPermissions(String userId);



}
