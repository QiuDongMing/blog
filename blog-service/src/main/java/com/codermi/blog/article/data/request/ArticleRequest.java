package com.codermi.blog.article.data.request;

import com.codermi.blog.user.data.request.group.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/30 18:36
 * @desc
 */
@Data
public class ArticleRequest {

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "文章标题不能为空", groups = AddGroup.class)
    private String title;

    @ApiModelProperty(value = "文章内容")
    @NotBlank(message = "文章内容不能为空" , groups = AddGroup.class)
    private String content;

    @ApiModelProperty(value = "文章标签")
    private List<String> labelNames;

    @ApiModelProperty(value = "文章分类")
    @NotBlank(message = "文章分类不能为空", groups = AddGroup.class)
    private String categoryId;

}
