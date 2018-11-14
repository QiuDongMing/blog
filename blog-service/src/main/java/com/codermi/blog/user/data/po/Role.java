package com.codermi.blog.user.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 15:26
 * @desc 角色
 */
@Document(collection = "t_role")
@Data
public class Role extends BaseInfo {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 角色描述
     */
    private String desc;

    /**
     * 权限列表
     */
    private List<String> permissionIds;
}
