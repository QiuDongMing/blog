package com.codermi.blog.user.dao;

import com.codermi.blog.user.data.po.Role;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 17:19
 * @desc
 */
public interface IRoleDao {

    List<Role> getByIds(List<ObjectId> objectIds);

    void insertRole(Role role);

    Role getByName(String roleName);


}
