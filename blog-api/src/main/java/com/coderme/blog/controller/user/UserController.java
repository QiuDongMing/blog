package com.coderme.blog.controller.user;

import com.alibaba.fastjson.JSON;
import com.coderme.blog.mylearnpackage.spring.application.ApplicationContextAwareImplements;
import com.coderme.blog.user.cache.data.dto.UserInfo;
import com.coderme.blog.user.service.IUserService;
import com.coderme.blog.user.service.impl.UserServiceImpl;
import com.coderme.common.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiudm
 * @date 2018/6/28 10:53
 * @desc
 */
@RequestMapping("user")
@RestController
@PreAuthorize("hasRole('USER')")
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
        System.out.println("bean.getBaseUserInfo = " + JSON.toJSONString(bean.getBaseUserInfo("1001")));

        return JsonResult.SUCCESS("测试");
    }

    @PostMapping("put")
    public JsonResult putTest(@RequestBody UserInfo userInfo) {
        userService.setBaseUserInfo(userInfo);
        return JsonResult.SUCCESS();
    }

    @GetMapping("get/{userId}")

    public JsonResult getTest(@PathVariable String userId) {
        UserInfo userInfo = userService.getBaseUserInfo(userId);
        return JsonResult.SUCCESS(userInfo);
    }



}
