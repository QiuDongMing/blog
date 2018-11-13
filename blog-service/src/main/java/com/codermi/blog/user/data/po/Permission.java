package com.codermi.blog.user.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

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
     * web端定义的路径
     */
    private String webUrl;

    /**
     * 权限列表 eg：user:add
     */
    private String perms;

    /**
     * 0-目录 1-菜单 2-按钮
     */
    private Integer type;

    /**
     * 排序
     */
    private Long sort;

}
