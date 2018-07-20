package com.coderme.blog.controller.user;

import com.coderme.blog.user.cache.data.dto.BaseUserInfo;
import com.coderme.blog.user.service.IUserService;
import com.coderme.common.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("test")
    public JsonResult test() {
        return JsonResult.SUCCESS("测试");
    }

    @PostMapping("put")
    public JsonResult putTest(@RequestBody BaseUserInfo baseUserInfo) {
        userService.setBaseUserInfo(baseUserInfo);
        return JsonResult.SUCCESS();
    }

    @GetMapping("get/{userId}")
    public JsonResult getTest(@PathVariable String userId) {
        BaseUserInfo baseUserInfo = userService.getBaseUserInfo(userId);
        return JsonResult.SUCCESS(baseUserInfo);
    }




}
