package com.coderme.blog.topic.data.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/6/28 13:50
 * @desc
 */
@Document(collection = "t_topic")
public class Topic {

    @Id
    private String id;

    private String userId;

    private Content content;









}
