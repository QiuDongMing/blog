package com.codermi.blog.article.dao;

import com.codermi.blog.article.data.po.ArticleCategory;

/**
 * @author qiudm
 * @date 2018/7/31 17:11
 * @desc 文章分类
 */
public interface IArticleCategoryDao {

    /**
     * 保存文章分类
     * @param articleCategory
     * @return
     */
    String saveArticleCategory(ArticleCategory articleCategory);

    /**
     * 获取分类
     * @param userId
     * @param name
     * @return
     */
    ArticleCategory getByUserIdName(String userId, String name);


}
