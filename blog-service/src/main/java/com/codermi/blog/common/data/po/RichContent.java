package com.codermi.blog.common.data.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/6/28 17:49
 * @desc
 */
@Document(collection = "t_rich_content")
@Data
public class RichContent {

    @Id
    private String id;

    private String content;

}
