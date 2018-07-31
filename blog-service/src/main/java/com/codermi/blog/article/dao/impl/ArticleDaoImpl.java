package com.codermi.blog.article.dao.impl;

import com.codermi.blog.article.dao.IArticleDao;
import com.codermi.blog.article.data.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    public void saveArticle(Article article) {
        mongoTemplate.save(article);
    }

}
