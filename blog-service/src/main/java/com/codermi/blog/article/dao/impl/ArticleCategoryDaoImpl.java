package com.codermi.blog.article.dao.impl;

import com.codermi.blog.article.dao.IArticleCategoryDao;
import com.codermi.blog.article.data.po.ArticleCategory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/7/31 17:12
 * @desc
 */
@Repository
public class ArticleCategoryDaoImpl implements IArticleCategoryDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveArticleCategory(ArticleCategory articleCategory) {
        mongoTemplate.save(articleCategory);
        return articleCategory.getId();
    }


    @Override
    public ArticleCategory getByUserIdName(String userId, String name) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("name").is(name));
        return mongoTemplate.findOne(query, ArticleCategory.class);
    }

    @Override
    public ArticleCategory getById(String id) {
        Query query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, ArticleCategory.class);
    }


    @Override
    public void update(String id, Map<String, Object> updateFieldMap) {
        if (Objects.isNull(updateFieldMap)
                && !updateFieldMap.isEmpty()) {
            return;
        }
        Update update = new Update();
        updateFieldMap.entrySet().forEach(entry->update.set(entry.getKey(), entry.getValue()));
        mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(new ObjectId(id))), update, ArticleCategory.class);
    }

    @Override
    public void delete(String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(new ObjectId(id))), ArticleCategory.class);
    }
}
