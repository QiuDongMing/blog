package com.codermi.blog.label.dao.impl;

import com.codermi.blog.label.dao.ILabelDao;
import com.codermi.blog.label.data.po.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author qiudm
 * @date 2018/7/30 10:29
 * @desc
 */
@Repository
public class LabelDaoImpl implements ILabelDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveLabel(Label label) {
        mongoTemplate.save(label);
        return label.getId();
    }

    @Override
    public Label getByNameType(String name, Integer type) {
        Query query = Query.query(Criteria.where("name").is(name).and("type").is(type));
        return mongoTemplate.findOne(query, Label.class);
    }
}
