package com.codermi.blog.article.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/7/30 10:54
 * @desc 文章分类
 */
@Document(collection = "t_article_category")
@Data
public class ArticleCategory extends BaseInfo {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 用户id
     */
    private String userId;

}
