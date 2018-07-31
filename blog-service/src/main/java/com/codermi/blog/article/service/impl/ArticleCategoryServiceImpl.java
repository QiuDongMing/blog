package com.codermi.blog.article.service.impl;

import com.codermi.blog.article.dao.IArticleCategoryDao;
import com.codermi.blog.article.data.po.ArticleCategory;
import com.codermi.blog.article.service.IArticleCategoryService;
import com.codermi.blog.common.utils.ReqUtil;
import com.codermi.blog.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String saveArticleCategory(String name) {
        String userId = ReqUtil.instance.getUserId();
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




}
