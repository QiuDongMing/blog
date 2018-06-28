package com.coderme.blog.common.service;

import com.coderme.blog.common.data.po.RichContent;

/**
 * @author qiudm
 * @date 2018/6/28 17:55
 * @desc
 */
public interface IRichContentService {


    void saveRichContent(RichContent richContent);


    RichContent getRichContentById(String id);




}
