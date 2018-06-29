package com.coderme.blog.common.service.impl;

import com.coderme.blog.common.dao.IRichContentDao;
import com.coderme.blog.common.data.po.RichContent;
import com.coderme.blog.common.service.IRichContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiudm
 * @date 2018/6/28 17:57
 * @desc
 */
@Service
public class RichContentServiceImpl implements IRichContentService {

    @Autowired
    private IRichContentDao richContentDao;

    @Override
    public String saveRichContent(RichContent richContent) {
      return richContentDao.saveRichContent(richContent);
    }

    @Override
    public RichContent getRichContentById(String id) {
        return richContentDao.getById(id);
    }

}
