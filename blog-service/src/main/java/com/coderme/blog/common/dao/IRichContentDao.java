package com.coderme.blog.common.dao;

import com.coderme.blog.common.data.po.RichContent;

/**
 * @author qiudm
 * @date 2018/6/28 17:51
 * @desc
 */
public interface IRichContentDao {

    void saveRichContent(RichContent richContent);

    RichContent getById(String id);

}
