package com.codermi.blog.user.data.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author qiudm
 * @date 2019/3/1 14:17
 * @desc
 */
@Data
public class PermissionResponse {

    @ApiModelProperty(value = "当前菜单的id")
    private String id;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "web端定义菜单url")
    private String webUrl;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "授权标识 eg：user:add")
    private String perm;

    @ApiModelProperty(value = "1-目录 2-菜单 3-按钮")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "子目录")
    private List<PermissionResponse> child;

}
