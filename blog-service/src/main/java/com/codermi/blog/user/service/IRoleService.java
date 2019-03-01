package com.codermi.blog.user.service;

import com.codermi.blog.user.data.request.RoleRequest;

/**
 * @author qiudm
 * @date 2018/11/14 14:22
 * @desc
 */
public interface IRoleService {

    /**
     * 新增角色
     * @param request
     */
    void addRole(RoleRequest request, String userId);






}
