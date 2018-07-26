package com.codermi.blog.common.service.impl;

import com.codermi.blog.common.dao.IIdIndexDao;
import com.codermi.blog.common.enums.CommonEnums.*;
import com.codermi.blog.common.service.IIdIndexService;
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
