package com.coderme.blog.user.service.impl;

import com.coderme.blog.user.cache.data.dto.UserInfo;
import com.coderme.blog.user.service.IUserService;
import com.coderme.blog.user.utils.UserCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiudm
 * @date 2018/6/28 11:34
 * @desc
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserCacheUtil userCacheUtil;

    @Override
    public UserInfo getBaseUserInfo(String userId) {
        return userCacheUtil.get(userId);
    }

    @Override
    public void setBaseUserInfo(UserInfo userInfo) {
        userCacheUtil.put(userInfo.getUserId(), userInfo);
    }
}
