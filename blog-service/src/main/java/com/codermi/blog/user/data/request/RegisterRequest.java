package com.codermi.blog.user.data.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qiudm
 * @date 2018/7/31 14:12
 * @desc
 */
@Data
public class RegisterRequest {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("邮箱")
    @Email
    private String email;

}
