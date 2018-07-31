package com.codermi.blog.article.service.impl;

import com.codermi.blog.article.dao.IArticleDao;
import com.codermi.blog.article.data.po.Article;
import com.codermi.blog.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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










}
