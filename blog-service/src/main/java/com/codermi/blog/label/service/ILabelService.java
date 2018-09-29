package com.codermi.blog.label.service;

import com.codermi.blog.label.data.po.Label;
import com.codermi.blog.label.enums.LabelType;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/30 10:40
 * @desc
 */
public interface ILabelService {
    /**
     * 添加标签
     * @param name
     * @param labelType
     * @return
     */
    String saveLabel(String name, LabelType labelType);

    /**
     * 通过名称和类型获取标签
     * @param name
     * @param labelType
     * @return
     */
    Label getByNameType(String name, LabelType labelType);


    /**
     * 通过名称和类型获取标签的id
     * @param names
     * @param labelType
     * @return
     */
    List<String> getIdsByNameType(List<String> names, LabelType labelType);

    /**
     * 增加标签的使用数
     * @param ids
     */
    void incrLabelUserCount(List<String> ids);


}
