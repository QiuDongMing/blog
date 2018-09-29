package com.codermi.blog.article.service.impl;

import com.codermi.blog.article.dao.IArticleDao;
import com.codermi.blog.article.data.po.Article;
import com.codermi.blog.article.data.po.ArticleCategory;
import com.codermi.blog.article.data.request.ArticleRequest;
import com.codermi.blog.article.service.IArticleCategoryService;
import com.codermi.blog.article.service.IArticleService;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.label.enums.LabelType;
import com.codermi.blog.label.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/7/30 11:03
 * @desc
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private IArticleCategoryService articleCategoryService;

    @Autowired
    private ILabelService labelService;


    @Override
    public String saveArticle(String userId, ArticleRequest articleRequest) {

        String categoryId = articleRequest.getCategoryId();
        ArticleCategory category = articleCategoryService.getById(categoryId);
        if (Objects.isNull(category)) {
            throw new ServiceException("该文章分类不存在");
        }
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setUserId(userId);
        article.setContent(articleRequest.getContent());
        article.setUpdateTime(System.currentTimeMillis());
        article.setCreateTime(System.currentTimeMillis());
        article.setCategoryId(categoryId);
        List<String> labelNames = articleRequest.getLabelNames();
        if (!CollectionUtils.isEmpty(labelNames)) {
            List<String> labelIds = labelService.getIdsByNameType(labelNames, LabelType.ARITCLE);
            article.setLabelIds(labelIds);
            labelService.incrLabelUserCount(labelIds);
        }

        return articleDao.saveArticle(article);
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
