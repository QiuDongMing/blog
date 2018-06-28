package com.coderme.blog.common.dao.impl;

import com.coderme.blog.common.dao.IIdIndexDao;
import com.coderme.blog.common.data.po.IdIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author qiudm
 * @date 2018/6/28 11:11
 * @desc
 */
@Repository
public class IdIndexDaoImpl implements IIdIndexDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Long getDataId(String dataType) {

        Query query = new Query(Criteria.where("dataType").is(dataType));
        Update update = new Update();
        update.inc("dataId", 1);
        IdIndex idIndex = mongoTemplate.findAndModify(query, update, IdIndex.class);

        return idIndex.getDataId();
    }
}
