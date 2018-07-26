package com.codermi.blog.article.data.po;

import com.codermi.blog.common.data.Content;
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
public class Article {

    @Id
    private String id;

    private String userId;

    private Content content;

    /**
     * 标签id
     */
    private List<String> labelIds;

    /**
     * 置顶时间
     */
    private Long stickTime;

    private Long updateTime;

    private Long createTime;

}
