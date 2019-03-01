package com.codermi.blog.article.service.impl;

import com.codermi.blog.article.dao.IArticleCategoryDao;
import com.codermi.blog.article.data.po.ArticleCategory;
import com.codermi.blog.article.service.IArticleCategoryService;
import com.codermi.blog.article.service.IArticleService;
import com.codermi.blog.exception.ServiceException;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/7/31 17:16
 * @desc
 */
@Service
public class ArticleCategoryServiceImpl implements IArticleCategoryService {

    @Autowired
    private IArticleCategoryDao articleCategoryDao;

    @Autowired
    private IArticleService articleService;


    @Override
    public String saveArticleCategory(String name, String userId) {
        System.out.println("userId = " + userId);
        ArticleCategory articleCategory = articleCategoryDao.getByUserIdName(userId, name.trim());
        if (Objects.nonNull(articleCategory)) {
            throw new ServiceException("该文章分类已经存在");
        }
        articleCategory = new ArticleCategory();
        articleCategory.setName(name.trim());
        articleCategory.setUserId(userId);
        articleCategory.setUpdateTime(System.currentTimeMillis());
        articleCategory.setCreateTime(System.currentTimeMillis());
        return articleCategoryDao.saveArticleCategory(articleCategory);
    }


    @Override
    public void updateArticleCategoryName(String userId, String id, String newName) {
        ArticleCategory articleCategory = articleCategoryDao.getById(id);
        if (articleCategory == null) {
            throw new ServiceException("不存在该文章");
        }
        if (!Objects.equals(userId, articleCategory.getUserId())) {
            throw new ServiceException("非创建者不能修改");
        }
        Map<String, Object> updateField = Maps.newHashMap();
        updateField.put("name", newName);
        updateField.put("updateTime", System.currentTimeMillis());
        articleCategoryDao.update(id, updateField);
    }


    @Override
    public void deleteArticleCategory(String userId, String id) {
        ArticleCategory articleCategory = articleCategoryDao.getById(id);
        if (articleCategory == null) {
            throw new ServiceException("不存在该文章");
        }
        if (!Objects.equals(userId, articleCategory.getUserId())) {
            throw new ServiceException("非创建者不能删除");
        }

        int articleCount = articleService.getCountByArticleCategory(userId, id);
        if(articleCount > 0) {
            throw new ServiceException("该分类下存在文章不能删除");
        }
        articleCategoryDao.delete(id);
    }

    @Override
    public ArticleCategory getById(String id) {
        return articleCategoryDao.getById(id);
    }



}
