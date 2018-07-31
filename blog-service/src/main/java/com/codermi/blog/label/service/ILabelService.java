package com.codermi.blog.label.service;

import com.codermi.blog.label.data.po.Label;

/**
 * @author qiudm
 * @date 2018/7/30 10:40
 * @desc
 */
public interface ILabelService {

    String saveLabel(String name, Integer type);

    Label getByNameType(String name, Integer type);

}
