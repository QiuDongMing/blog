package com.codermi.blog.article.dao.impl;

import com.codermi.blog.article.dao.IArticleCategoryDao;
import com.codermi.blog.article.data.po.ArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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
}
