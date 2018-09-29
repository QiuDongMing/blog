package com.codermi.blog.label.service.impl;

import com.codermi.blog.label.dao.ILabelDao;
import com.codermi.blog.label.data.po.Label;
import com.codermi.blog.label.enums.LabelType;
import com.codermi.blog.label.service.ILabelService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public String saveLabel(String name, LabelType labelType) {
        Label label = new Label();
        label.setName(name);
        label.setType(labelType.getType());
        label.setUseCount(0);
        label.setUpdateTime(System.currentTimeMillis());
        label.setCreateTime(System.currentTimeMillis());
        return labelDao.saveLabel(label);
    }

    @Override
    public Label getByNameType(String name, LabelType labelType) {
        return labelDao.getByNameType(name, labelType.getType());
    }


    @Override
    public List<String> getIdsByNameType(List<String> names, LabelType labelType) {
        List<String> ids = Lists.newArrayList();
        for (String name : names) {
            if (StringUtils.isBlank(name)) {
                continue;
            }
            Label label = getByNameType(name, labelType);
            if (Objects.nonNull(label)) {
                ids.add(label.getId());
            } else {
                String id = saveLabel(name, labelType);
                ids.add(id);
            }
        }
        return ids;
    }

    @Async
    @Override
    public void incrLabelUserCount(List<String> ids) {
        labelDao.incrLabelUserCount(ids);
    }
}
