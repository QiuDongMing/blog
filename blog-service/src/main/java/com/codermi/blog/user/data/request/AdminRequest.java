package com.codermi.blog.user.data.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/14 10:15
 * @desc 管理员新增更新请求
 */
@Data
public class AdminRequest {

    @ApiModelProperty(value = "管理员登录名")
    @NotBlank(message = "管理员登录账号不能为空")
    private String username;

    @ApiModelProperty(value = "管理员昵称")
    private String nickname;

    @ApiModelProperty(value = "登录密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;

    private List<String> roleIds;
}
