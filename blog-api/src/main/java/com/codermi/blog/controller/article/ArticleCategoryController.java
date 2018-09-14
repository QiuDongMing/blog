package com.codermi.blog.controller.article;

import com.codermi.blog.article.service.IArticleCategoryService;
import com.codermi.common.base.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author qiudm
 * @date 2018/7/31 18:23
 * @desc
 */
@Api(value = "文章分类相关")
@RequestMapping("article/category")
@RestController
public class ArticleCategoryController {

    @Autowired
    private IArticleCategoryService articleCategoryService;

    @ApiOperation(value = "新增文章分类", httpMethod = "POST" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access-token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/save")
    public JsonResult save(@RequestParam @NotEmpty String name) {
        return JsonResult.SUCCESS(articleCategoryService.saveArticleCategory(name));
    }






}
