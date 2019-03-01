package com.codermi.blog.controller.article;

import com.codermi.blog.article.service.IArticleCategoryService;
import com.codermi.common.base.utils.JsonResult;
import com.codermi.sercurity.utils.ReqUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author qiudm
 * @date 2018/7/31 18:23
 * @desc
 */
@Api(value = "文章分类相关")
@RequestMapping("article/category")
@RestController
@Validated
public class ArticleCategoryController {

    @Autowired
    private IArticleCategoryService articleCategoryService;

    @ApiOperation(value = "新增文章分类", httpMethod = "POST" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/save")
    public JsonResult save(@NotBlank(message = "文章分类名称不能为空") @RequestParam String name) {
        return JsonResult.SUCCESS(articleCategoryService.saveArticleCategory(name, ReqUtil.instance.getUserId()));
    }
}
