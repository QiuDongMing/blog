package com.codermi.blog.user.dao;

import com.codermi.blog.user.data.po.User;

/**
 * @author qiudm
 * @date 2018/6/28 11:31
 * @desc
 */
public interface IUserDao {

    void saveUser(User user);

    User getByNickName(String nickName);

    User getByOpenId(String openId);

    User getByUserId(String userId);

}
