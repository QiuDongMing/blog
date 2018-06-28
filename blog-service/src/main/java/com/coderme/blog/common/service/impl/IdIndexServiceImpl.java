package com.coderme.blog.common.service.impl;

import com.coderme.blog.common.dao.IIdIndexDao;
import com.coderme.blog.common.enums.CommonEnums.*;
import com.coderme.blog.common.service.IIdIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiudm
 * @date 2018/6/28 11:28
 * @desc
 */
@Service
public class IdIndexServiceImpl implements IIdIndexService {

    @Autowired
    private IIdIndexDao indexDao;

    @Override
    public String getNextUserId() {

        return String.valueOf(indexDao.getDataId(IdIndexDataType.userId.name()));
    }
}
