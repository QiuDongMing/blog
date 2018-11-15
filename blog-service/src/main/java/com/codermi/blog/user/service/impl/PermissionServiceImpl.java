package com.codermi.blog.user.service.impl;

import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.constants.PermConstants;
import com.codermi.blog.user.dao.IPermissionDao;
import com.codermi.blog.user.data.po.Permission;
import com.codermi.blog.user.data.request.PermissionRequest;
import com.codermi.blog.user.enums.PermissionEnums.*;
import com.codermi.blog.user.service.IPermissionService;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.StringUtils;
import com.codermi.common.base.utils.TimeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/11/14 15:11
 * @desc
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void addPermission(PermissionRequest request) {
        int type = request.getType();
        Permission prentPermission = permissionDao.getById(request.getPid());
        if (Objects.isNull(prentPermission)) {
            throw new ServiceException("父级菜单不存在");
        }

        Permission permission = BeanUtil.copy(request, Permission.class);
        permission.setUpdateTime(TimeUtils.currentMillis());
        permission.setCreateTime(TimeUtils.currentMillis());
        permission.setSort(TimeUtils.currentMillis());

        //目录
        if (Objects.equals(type, PermissionType.DIRECTORY.getType())) {
            permission.setPerm(null);
            permission.setWebUrl(null);
            permissionDao.insertPermission(permission);
            return;
        }

        //菜单
        if (Objects.equals(type, PermissionType.MENU.getType())) {
            if (StringUtils.isBlank(request.getWebUrl())) {
                throw new ServiceException("菜单URL不能为空");
            }
            permissionDao.insertPermission(permission);
            return;
        }

        //按钮
        if (Objects.equals(type, PermissionType.BUTTON.getType())) {
            if (StringUtils.isBlank(permission.getPerm())) {
                throw new ServiceException("权限标识不能为空");
            }
            permission.setWebUrl(null);
            permissionDao.insertPermission(permission);
            return;
        }
    }


    @Override
    public Permission getPermsList() {
        String firstMenuId = PermConstants.FIRST_MENU_ID;
        Permission res = new Permission();
        res.setId(firstMenuId);
        res.setName(PermConstants.FIRST_MENU_NAME);
        res.setIcon(PermConstants.FIRST_MENU_ICON);
        List<Permission> childs = permissionDao.getByPid(firstMenuId);
        getByPid(childs);
        res.setChild(childs);
        return res;
    }


    private void getByPid(List<Permission> childs) {
        if (CollectionUtils.isEmpty(childs)) {
            return;
        }
        for (Permission permission : childs) {
            List<Permission> child = permissionDao.getByPid(permission.getId());
            if (CollectionUtils.isNotEmpty(child)) {
                permission.setChild(child);
                this.getByPid(child);
            }
        }
    }



}
