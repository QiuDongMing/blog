package com.codermi.blog.common.service;

import com.codermi.blog.common.data.po.RichContent;

/**
 * @author qiudm
 * @date 2018/6/28 17:55
 * @desc
 */
public interface IRichContentService {


    String saveRichContent(RichContent richContent);


    RichContent getRichContentById(String id);



}
