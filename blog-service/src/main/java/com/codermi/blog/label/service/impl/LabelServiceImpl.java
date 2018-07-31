package com.codermi.blog.label.service.impl;

import com.codermi.blog.label.dao.ILabelDao;
import com.codermi.blog.label.data.po.Label;
import com.codermi.blog.label.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiudm
 * @date 2018/7/30 10:43
 * @desc
 */
@Service
public class LabelServiceImpl implements ILabelService {
    @Autowired
    private ILabelDao labelDao;

    @Override
    public String saveLabel(String name, Integer type) {
        Label label = new Label();
        label.setName(name);
        label.setType(type);
        label.setUseCount(0);
        label.setUpdateTime(System.currentTimeMillis());
        label.setCreateTime(System.currentTimeMillis());
        return labelDao.saveLabel(label);
    }

    @Override
    public Label getByNameType(String name, Integer type) {
        return labelDao.getByNameType(name, type);
    }
}
