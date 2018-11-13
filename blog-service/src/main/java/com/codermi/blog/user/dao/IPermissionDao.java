package com.codermi.blog.user.dao;

import com.codermi.blog.user.data.po.Permission;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/13 17:13
 * @desc
 */
public interface IPermissionDao {


    List<Permission> getByIds(List<ObjectId> objectIds);


}
