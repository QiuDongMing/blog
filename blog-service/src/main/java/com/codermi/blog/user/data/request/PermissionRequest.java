package com.codermi.blog.user.data.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author qiudm
 * @date 2018/11/14 14:54
 * @desc
 */
@Data
public class PermissionRequest {

    @ApiModelProperty(value = "父菜单的id，类型为菜单和按钮时传")
    @NotBlank(message = "父菜单id不能为空")
    private String pid;

    @ApiModelProperty(value = "web端定义的菜单url")
    private String webUrl;

    @ApiModelProperty(value = "权限的名称")
    @NotBlank(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty(value = "授权标识 eg：user:add")
    private String perm;

    @ApiModelProperty(value = "权限类型:1-目录 2-菜单 3-按钮")
    @Range(min = 1, max = 3, message = "权限类型错误")
    private int type;

    @ApiModelProperty(value = "图标")
    private String icon;

}
