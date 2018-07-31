package com.codermi.blog.label.dao;

import com.codermi.blog.label.data.po.Label;

/**
 * @author qiudm
 * @date 2018/7/30 10:28
 * @desc
 */
public interface ILabelDao {

    String saveLabel(Label label);

    Label getByNameType(String name, Integer type);

}
