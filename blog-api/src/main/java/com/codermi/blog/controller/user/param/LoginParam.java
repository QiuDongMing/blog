package com.codermi.blog.controller.user.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qiudm
 * @date 2018/7/27 2:53
 * @desc 登录参数
 */
@Data
public class LoginParam {

    @NotBlank(message = "登录名称不能为空")
    private String nickName;

    @NotBlank(message = "登录密码不能为空")
    private String password;


}
