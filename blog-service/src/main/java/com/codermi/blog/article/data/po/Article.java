package com.codermi.blog.article.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/6/28 13:50
 * @desc
 */
@Document(collection = "t_article")
@Data
public class Article extends BaseInfo {

    private String userId;

    /**
     * 文章分类Id
     */
    private String categoryId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 标签id
     */
    private List<String> labelIds;

    /**
     * 置顶时间
     */
    private Long stickTime;

}
