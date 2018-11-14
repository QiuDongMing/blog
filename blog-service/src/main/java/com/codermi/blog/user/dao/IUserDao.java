package com.codermi.blog.user.dao;

import com.codermi.blog.user.data.po.User;

import java.util.Map;

/**
 * @author qiudm
 * @date 2018/6/28 11:31
 * @desc
 */
public interface IUserDao {

    void saveUser(User user);

    User getByUserNameAndType(String username, Integer userType);

    User getByOpenId(String openId);

    User getByUserId(String userId);

    void updateUser(String userId, Map<String, Object> updates);
}
