package com.codermi.blog.user.service;

import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.data.request.RegisterRequest;

/**
 * @author qiudm
 * @date 2018/9/6 15:31
 * @desc
 */
public interface ISecurityService {

    /**
     * 根据昵称密码登陆
     * @param nickName
     * @param password
     * @return
     */
    AccessToken loginByNickNamePassword(String nickName, String password);

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





}
