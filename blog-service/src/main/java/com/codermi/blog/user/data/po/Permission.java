package com.codermi.blog.user.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 15:29
 * @desc 权限表
 */
@Document(collection = "t_permission")
@Data
public class Permission extends BaseInfo {

    /**
     * 父级id
     */
    private String pid;

    /**
     * web端定义菜单url
     */
    private String webUrl;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 授权标识 eg：user:add
     */
    private String perms;

    /**
     * 1-目录 2-菜单 3-按钮
     */
    private Integer type;

    /**
     * 排序
     */
    private Long sort;

    @Transient
    private List<Permission> child;

}
