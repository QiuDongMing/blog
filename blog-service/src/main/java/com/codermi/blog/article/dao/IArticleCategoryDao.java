package com.codermi.blog.article.dao;

import com.codermi.blog.article.data.po.ArticleCategory;

import java.util.Map;

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


    /**
     * 通过主键获取文章id
     * @param id
     * @return
     */
    ArticleCategory getById(String id);

    /**
     * 更新分类
     * @param id
     * @param updateFieldMap
     */
    void update(String id, Map<String, Object> updateFieldMap);

    /**
     * 删除文章分类
     * @param id
     */
    void delete(String id);


}
