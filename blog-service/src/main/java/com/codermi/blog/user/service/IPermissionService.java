package com.codermi.blog.user.service;

import com.codermi.blog.user.data.po.Permission;
import com.codermi.blog.user.data.request.PermissionRequest;
import com.codermi.blog.user.data.response.PermissionResponse;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/14 14:42
 * @desc
 */
public interface IPermissionService {


    /**
     * 新增权限
     * @param request
     */
    void addPermission(PermissionRequest request);

    /**
     * 获取权限列表
     * @return
     */
    PermissionResponse getPermsList();







}
