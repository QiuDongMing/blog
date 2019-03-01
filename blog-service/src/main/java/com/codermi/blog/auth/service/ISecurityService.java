package com.codermi.blog.auth.service;

import com.codermi.blog.auth.data.vo.LoginUserInfo;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.data.po.Permission;
import com.codermi.blog.user.data.request.AdminRequest;
import com.codermi.blog.user.data.request.RegisterRequest;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 14:22
 * @desc
 */
public interface ISecurityService {

    /**
     * 使用spring-security登录
     * 根据账号密码登陆
     * @param username
     * @param password
     * @return
     */
    @Deprecated
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

    /**
     * 新增管理员
     * @param param
     */
    void addAdmin(AdminRequest param);

    /**
     * 根据用户名称和用户类型获取用户
     * @param username
     * @param type
     * @return
     */
    LoginUserInfo getLoginUserInfoByUserNameAndType(String username, Integer type);


}
