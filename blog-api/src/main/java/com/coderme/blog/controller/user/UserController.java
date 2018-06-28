package com.coderme.blog.controller.user;

import com.coderme.common.base.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiudm
 * @date 2018/6/28 10:53
 * @desc
 */
@RequestMapping("user")
@RestController
public class UserController {

    @GetMapping("test")
    public JsonResult test() {
        return JsonResult.SUCCESS("测试");
    }




}
