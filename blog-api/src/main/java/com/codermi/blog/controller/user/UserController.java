package com.codermi.blog.controller.user;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.controller.user.param.LoginParam;
import com.codermi.blog.controller.user.param.RegisterParam;
import com.codermi.blog.mylearnpackage.spring.application.ApplicationContextAwareImplements;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.service.impl.UserServiceImpl;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.JsonResult;
import com.codermi.sercurity.utils.JwtTokenUtil;
import com.google.common.collect.Maps;
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



    @PostMapping("/register")
    public JsonResult login(@RequestBody @Valid RegisterParam param) {
        User user = BeanUtil.copy(param, User.class);
        userService.register(user);
        return JsonResult.SUCCESS();
    }


    @PostMapping("/login")
    public JsonResult login(@RequestBody @Valid LoginParam param) {
        UserOpenInfo userOpenInfo = userService.loginByNickNamePassword(param.getNickName(), param.getPassword());
        Map<String, String> map = Maps.newHashMap();
        map.put("accessToken", JwtTokenUtil.generateToken(userOpenInfo));
        return JsonResult.SUCCESS(map);
    }




}
