package com.codermi.blog.article.data.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/30 18:36
 * @desc
 */
@Data
public class ArticleRequest {

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章标签")
    private List<String> labelNames;

    @ApiModelProperty(value = "文章分类")
    private String categoryId;

}
