package com.codermi.blog.article.dao.impl;

import com.codermi.blog.article.dao.IArticleDao;
import com.codermi.blog.article.data.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author qiudm
 * @date 2018/7/30 11:00
 * @desc
 */
@Repository
public class ArticleDaoImpl implements IArticleDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveArticle(Article article) {
        mongoTemplate.save(article);
        return article.getId();
    }


    @Override
    public int getCountByArticleCategory(String userId, String articleCategoryId) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("articleCategoryId").is(articleCategoryId));
        long count = mongoTemplate.count(query, Article.class);
        return (int) count;
    }
}
