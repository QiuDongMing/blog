package com.codermi.blog.common.dao.impl;

import com.codermi.blog.common.dao.IRichContentDao;
import com.codermi.blog.common.data.po.RichContent;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author qiudm
 * @date 2018/6/28 17:53
 * @desc
 */
@Repository
public class RichContentDaoImpl implements IRichContentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveRichContent(RichContent richContent) {
        mongoTemplate.save(richContent);
        return richContent.getId();
    }

    @Override
    public RichContent getById(String id) {
        return mongoTemplate.findById(new ObjectId(id), RichContent.class);
    }
}
