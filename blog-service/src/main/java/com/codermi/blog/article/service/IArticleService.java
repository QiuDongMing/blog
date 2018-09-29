package com.codermi.blog.article.service;

import com.codermi.blog.article.data.po.Article;
import com.codermi.blog.article.data.request.ArticleRequest;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/30 11:02
 * @desc
 */
public interface IArticleService {

    /**
     * 新增文章
     * @param userId 发布者
     * @param articleRequest
     */
    String saveArticle(String userId, ArticleRequest articleRequest);

    /**
     * 获取某分类下的文章列表(应该要分页)
     * @param userId
     * @param articleCategoryId
     * @return
     */
    List<Article> getByArticleCategory(String userId, String articleCategoryId);

    /**
     * 获取某分类下的文章数目
     * @param userId
     * @param articleCategoryId
     * @return
     */
    int getCountByArticleCategory(String userId, String articleCategoryId);







}
