package com.codermi.blog.user.dao.impl;

import com.codermi.blog.user.dao.IRoleDao;
import com.codermi.blog.user.data.po.Role;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 17:20
 * @desc
 */
@Repository
public class RoleDaoImpl implements IRoleDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Role> getByIds(List<ObjectId> objectIds) {
        Query query = Query.query(Criteria.where("_id").in(objectIds));
        return mongoTemplate.find(query, Role.class);
    }







}
