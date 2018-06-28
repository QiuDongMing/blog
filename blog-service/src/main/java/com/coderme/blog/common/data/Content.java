package com.coderme.blog.common.data;

import lombok.Data;

import java.util.List;

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

    /**
     * 标题
     */
    private String title;

    /**
     * 内容（不存储富文本）
     */
    private String content;

    /**
     * 富文本内容id，目前仅有富文本
     */
    private String contentId;
    /**
     * 封面
     */
    private List<String> coverUrls;



}
