package com.codermi.blog.controller.article;

import com.codermi.blog.article.data.request.ArticleRequest;
import com.codermi.common.base.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author qiudm
 * @date 2018/7/31 14:58
 * @desc
 */
@Api(value = "文章相关")
@RequestMapping("article")
@RestController
public class ArticleController {

    @ApiOperation(value = "发布文章", httpMethod = "POST" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access-token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "参数请求体", value = "param", dataType = "ArticleRequest", paramType = "body")
    })
    @PostMapping("/publish")
    public JsonResult publish(@RequestBody @Valid ArticleRequest articleRequest) {

        return JsonResult.SUCCESS();
    }

}
