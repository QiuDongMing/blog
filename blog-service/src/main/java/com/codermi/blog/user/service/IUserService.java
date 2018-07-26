package com.codermi.blog.user.service;

import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.blog.user.data.po.User;

/**
 * @author qiudm
 * @date 2018/6/28 10:31
 * @desc
 */
public interface IUserService {

    UserInfo getBaseUserInfo(String userId);

    void setBaseUserInfo(UserInfo userInfo);

    UserOpenInfo loginByNickNamePassword(String nickName, String password);

    /**
     * 注册
     * @param user
     */
    void register(User user);


}
