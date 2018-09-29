package com.codermi.blog.article.service;


/**
 * @author qiudm
 * @date 2018/7/31 17:14
 * @desc 文章的分类
 */
public interface IArticleCategoryService {

    /**
     * 新增文章分类
     * @param name
     * @return
     */
    String saveArticleCategory(String name);

    /**
     * 更新文章分类的名称
     * @param  userId   当前登录用户的id
     * @param id        文章的id
     * @param newName   文章的新名称
     */
    void updateArticleCategoryName(String userId, String id, String newName);

    /**
     * 删除文章分类
     * @param id
     */
    void deleteArticleCategory(String userId, String id);


}
