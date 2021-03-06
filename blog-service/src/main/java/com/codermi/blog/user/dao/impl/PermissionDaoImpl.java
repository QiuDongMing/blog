package com.codermi.blog.user.dao.impl;

import com.codermi.blog.user.dao.IPermissionDao;
import com.codermi.blog.user.data.po.Permission;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 17:15
 * @desc
 */
@Repository
public class PermissionDaoImpl implements IPermissionDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Permission> getByIds(List<ObjectId> objectIds) {
        Query query = Query.query(Criteria.where("_id").in(objectIds));
        return mongoTemplate.find(query, Permission.class);
    }

    @Override
    public Permission getById(String id) {
        Query query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, Permission.class);
    }

    @Override
    public void insertPermission(Permission permission) {
        mongoTemplate.insert(permission);
    }


    @Override
    public List<Permission> getByPid(String pid) {
        Query query = Query.query(Criteria.where("pid").is(pid));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        return mongoTemplate.find(query, Permission.class);
    }




}
