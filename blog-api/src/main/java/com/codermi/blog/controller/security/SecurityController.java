package com.codermi.blog.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiudm
 * @date 2018/12/5 15:29
 * @desc
 */
@Api(value = "安全相关")
@RestController
public class SecurityController {



    @ApiOperation(value = "用户表单登录,会被spring-security拦截,仅为了swagger", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userType", value = "用户类型0-管理员 1-普通用户", required = true, dataType = "Integer", paramType = "query")
    })
    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password, @RequestParam Integer userType) {

    }







}
