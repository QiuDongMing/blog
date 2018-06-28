package com.coderme.blog.user.dao.impl;

import com.coderme.blog.user.dao.IUserDao;
import com.coderme.blog.user.data.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author qiudm
 * @date 2018/6/28 11:32
 * @desc
 */
@Service
public class UserDaoImpl implements IUserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }




}
