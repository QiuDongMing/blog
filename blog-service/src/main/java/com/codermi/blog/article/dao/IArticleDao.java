package com.codermi.blog.article.dao;

import com.codermi.blog.article.data.po.Article;

/**
 * @author qiudm
 * @date 2018/7/30 10:59
 * @desc
 */
public interface IArticleDao {

    String saveArticle(Article article);


    int getCountByArticleCategory(String userId, String articleCategoryId);


}
