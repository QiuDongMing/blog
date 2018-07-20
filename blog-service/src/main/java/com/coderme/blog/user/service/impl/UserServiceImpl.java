package com.coderme.blog.user.service.impl;

import com.coderme.blog.user.cache.UserCache;
import com.coderme.blog.user.cache.data.dto.BaseUserInfo;
import com.coderme.blog.user.service.IUserService;
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
    private UserCache userCache;

    @Override
    public BaseUserInfo getBaseUserInfo(String userId) {
        return userCache.get(userId);
    }

    @Override
    public void setBaseUserInfo(BaseUserInfo baseUserInfo) {
        userCache.put(baseUserInfo.getUserId(), baseUserInfo);
    }
}
