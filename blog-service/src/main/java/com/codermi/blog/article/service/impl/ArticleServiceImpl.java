package com.codermi.blog.article.service.impl;

import com.codermi.blog.article.dao.IArticleDao;
import com.codermi.blog.article.data.po.Article;
import com.codermi.blog.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/30 11:03
 * @desc
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Override
    public void saveArticle(Article article) {


    }


    @Override
    public List<Article> getByArticleCategory(String userId, String articleCategoryId) {
        return null;
    }


    @Override
    public int getCountByArticleCategory(String userId, String articleCategoryId) {
        return articleDao.getCountByArticleCategory(userId, articleCategoryId);
    }



}
