package com.codermi.blog.controller.user;

import com.codermi.blog.auth.service.ISecurityService;
import com.codermi.blog.mylearnpackage.spring.application.ApplicationContextAwareImplements;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.data.request.AdminRequest;
import com.codermi.blog.user.data.request.LoginRequest;
import com.codermi.blog.user.data.request.RegisterRequest;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.service.impl.UserServiceImpl;
import com.codermi.common.base.utils.JsonResult;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author qiudm
 * @date 2018/6/28 10:53
 * @desc
 */
@Api(value = "用户相关")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private ApplicationContextAwareImplements applicationContextAwareImplements;

//    @PreAuthorize("isAuthenticated()")
//    @Secured({"ROLE_USER"})
    @RequestMapping("test")
    public JsonResult test() {
        ApplicationContext applicationContext = applicationContextAwareImplements.getApplicationContext();

        System.out.println("applicationContext.getApplicationName() = " + applicationContext.getApplicationName());
        System.out.println("applicationContext.getId() = " + applicationContext.getId());
        UserServiceImpl bean = applicationContext.getBean(UserServiceImpl.class);


        return JsonResult.SUCCESS("测试");
    }

    @RequestMapping("/open/test")
    public JsonResult open() {

        int a = 1 /0 ;

//        try {
           // Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        userService.test();
        return JsonResult.SUCCESS("测试open");
    }

    @ApiOperation(value = "用户注册", httpMethod = "POST" )
    @PostMapping("/register")
    public JsonResult login(@RequestBody @Valid RegisterRequest param) {
        securityService.register(param);
        return JsonResult.SUCCESS();
    }


    @ApiOperation(value = "用户登录", httpMethod = "POST", response = AccessToken.class)
    @PostMapping("/login")
    public JsonResult login(@RequestBody @Validated LoginRequest param) {
        AccessToken accessToken = securityService.loginByUserNamePassword(param.getUsername(), param.getPassword(), param.getUserType());
        Map<String, Object> map = Maps.newHashMap();
        map.put("accessToken", accessToken);
        return JsonResult.SUCCESS(map);
    }


    @ApiOperation(value = "新增管理员用户", httpMethod = "POST")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:add')")
    public JsonResult addAdmin(@RequestBody @Validated AdminRequest param) {
        System.out.println(" 有权限 " );

        return JsonResult.SUCCESS();
    }


}
