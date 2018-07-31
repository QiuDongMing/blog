package com.codermi.blog.controller.user;

import com.codermi.blog.mylearnpackage.spring.application.ApplicationContextAwareImplements;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.blog.user.data.request.LoginRequest;
import com.codermi.blog.user.data.request.RegisterRequest;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.service.impl.UserServiceImpl;
import com.codermi.common.base.utils.JsonResult;
import com.codermi.sercurity.utils.JwtTokenUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author qiudm
 * @date 2018/6/28 10:53
 * @desc
 */
@Api(value = "用户相关")
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationContextAwareImplements applicationContextAwareImplements;

    @GetMapping("test")
    public JsonResult test() {
        ApplicationContext applicationContext = applicationContextAwareImplements.getApplicationContext();

        System.out.println("applicationContext.getApplicationName() = " + applicationContext.getApplicationName());
        System.out.println("applicationContext.getId() = " + applicationContext.getId());
        UserServiceImpl bean = applicationContext.getBean(UserServiceImpl.class);

        return JsonResult.SUCCESS("测试");
    }


    @ApiOperation(value = "用户注册", httpMethod = "POST" )
    @PostMapping("/register")
    public JsonResult login(@RequestBody @Valid RegisterRequest param) {
        userService.register(param);
        return JsonResult.SUCCESS();
    }


    @ApiOperation(value = "用户登录", httpMethod = "POST", response = UserOpenInfo.class)
    @PostMapping("/login")
    public JsonResult login(@RequestBody @Valid LoginRequest param) {
        UserInfo userInfo = userService.loginByNickNamePassword(param.getNickName(), param.getPassword());
        Map<String, String> map = Maps.newHashMap();
        map.put("accessToken", JwtTokenUtil.generateToken(userInfo));
        return JsonResult.SUCCESS(map);
    }



}
