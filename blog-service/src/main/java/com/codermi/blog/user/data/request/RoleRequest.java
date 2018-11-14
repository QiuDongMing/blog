package com.codermi.blog.user.data.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/14 14:24
 * @desc
 */
@Data
public class RoleRequest {

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String desc;

    @ApiModelProperty(value = "角色的权限")
    private List<String> permissionIds;

}
