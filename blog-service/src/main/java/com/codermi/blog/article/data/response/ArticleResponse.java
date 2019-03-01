package com.codermi.blog.article.data.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/9/29 18:00
 * @desc 文章详情
 */
@Data
public class ArticleResponse {

    @ApiModelProperty(value = "文章id")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "标签名称")
    private List<String> labelNames;

    @ApiModelProperty(value = "置顶时间")
    private Long stickTime;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;




}
