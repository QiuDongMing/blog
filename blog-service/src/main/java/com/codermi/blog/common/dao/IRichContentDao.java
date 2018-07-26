package com.codermi.blog.common.dao;

import com.codermi.blog.common.data.po.RichContent;

/**
 * @author qiudm
 * @date 2018/6/28 17:51
 * @desc
 */
public interface IRichContentDao {

    String saveRichContent(RichContent richContent);

    RichContent getById(String id);

}
