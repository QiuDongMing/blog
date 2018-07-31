package com.codermi.blog.user.data.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qiudm
 * @date 2018/7/31 14:12
 * @desc
 */
@Data
public class LoginRequest {

    @ApiModelProperty(value = "登录名")
    @NotBlank(message = "登录名称不能为空")
    private String nickName;

    @ApiModelProperty(value = "登录密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;

}
