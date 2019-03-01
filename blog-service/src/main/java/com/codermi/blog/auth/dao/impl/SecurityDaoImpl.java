package com.codermi.blog.auth.dao.impl;

import com.codermi.blog.auth.dao.ISecurityDao;
import com.codermi.blog.auth.data.po.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author qiudm
 * @date 2018/11/13 14:27
 * @desc
 */
@Repository
public class SecurityDaoImpl implements ISecurityDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Account getByAccountNumAndUserType(String accountNum, Integer userType) {
        return mongoTemplate.findOne(Query.query(Criteria.where("accountNum").is(accountNum)
                .and("userType").is(userType)), Account.class);
    }

    @Override
    public Account getByAccountNum(String accountNum) {
        return mongoTemplate.findOne(Query.query(Criteria.where("accountNum").is(accountNum)), Account.class);
    }

    @Override
    public void insertAccount(Account account) {
        mongoTemplate.insert(account);
    }


}
