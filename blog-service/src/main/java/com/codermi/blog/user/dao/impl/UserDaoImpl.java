package com.codermi.blog.user.dao.impl;

import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Override
    public User getByNickName(String nickName) {
        Query query = Query.query(Criteria.where("nickName").is(nickName));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User getByOpenId(String openId) {
        Query query = Query.query(Criteria.where("openId").is(openId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User getByUserId(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }
}
