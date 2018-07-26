package com.codermi.blog.controller.user.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qiudm
 * @date 2018/7/27 4:45
 * @desc 注册参数
 */
@Data
public class RegisterParam {

    private String nickName;

    @NotBlank(message = "密码不能为空")
    private String password;


    private String email;





}
