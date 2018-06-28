package com.coderme.blog.common.data;

import lombok.Data;

/**
 * @author qiudm
 * @date 2018/6/28 13:56
 * @desc
 */
@Data
public class Content {

    /**
     * @see com.coderme.blog.common.enums.CommonEnums.ContentType
     */
    private Integer contentType;

    private String title;

    private String content;

    private String contentId;

    private String h5Url;


}
