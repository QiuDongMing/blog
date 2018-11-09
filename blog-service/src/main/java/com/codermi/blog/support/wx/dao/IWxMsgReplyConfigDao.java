package com.codermi.blog.support.wx.dao;

import com.codermi.blog.support.wx.data.po.WxMsgReplyConfig;

/**
 * @author qiudm
 * @date 2018/11/9 11:11
 * @desc
 */
public interface IWxMsgReplyConfigDao {


    String saveWxMsgReplyConfig(WxMsgReplyConfig wxMsgReplyConfig);


    WxMsgReplyConfig getByReqText(String reqText);






}
