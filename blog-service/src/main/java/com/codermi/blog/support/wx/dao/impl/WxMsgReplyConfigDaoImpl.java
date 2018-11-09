package com.codermi.blog.support.wx.dao.impl;

import com.codermi.blog.support.wx.dao.IWxMsgReplyConfigDao;
import com.codermi.blog.support.wx.data.po.WxMsgReplyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author qiudm
 * @date 2018/11/9 11:12
 * @desc
 */
@Repository
public class WxMsgReplyConfigDaoImpl implements IWxMsgReplyConfigDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveWxMsgReplyConfig(WxMsgReplyConfig wxMsgReplyConfig) {
        mongoTemplate.insert(wxMsgReplyConfig);
        return wxMsgReplyConfig.getId();
    }

    @Override
    public WxMsgReplyConfig getByReqText(String reqText) {
        WxMsgReplyConfig msgReplyConfig = mongoTemplate
                .findOne(Query.query(Criteria.where("reqText").is(reqText)), WxMsgReplyConfig.class);
        return msgReplyConfig;
    }






}
