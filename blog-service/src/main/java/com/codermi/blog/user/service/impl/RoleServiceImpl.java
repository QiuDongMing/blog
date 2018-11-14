package com.codermi.blog.user.service.impl;

import com.codermi.blog.common.utils.ReqUtil;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.dao.IRoleDao;
import com.codermi.blog.user.data.po.Role;
import com.codermi.blog.user.data.request.RoleRequest;
import com.codermi.blog.user.service.IRoleService;
import com.codermi.common.base.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/11/14 14:22
 * @desc 角色管理
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public void addRole(RoleRequest roleRequest) {
        Role dbRole = roleDao.getByName(roleRequest.getRoleName().trim());
        if (Objects.nonNull(dbRole)) {
            throw new ServiceException("该角色已经存在");
        }

        Role role = BeanUtil.copy(roleRequest, Role.class);
        role.setCreator(ReqUtil.instance.getUserId());
        long time = System.currentTimeMillis();
        role.setCreateTime(time);
        role.setUpdateTime(time);
        roleDao.insertRole(role);
    }





}
