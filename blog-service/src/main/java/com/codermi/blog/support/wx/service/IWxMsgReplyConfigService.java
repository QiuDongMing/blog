package com.codermi.blog.support.wx.service;

import com.codermi.blog.support.wx.data.po.WxMsgReplyConfig;
import com.codermi.blog.support.wx.data.request.WxMsgReplyConfigRequest;

/**
 * @author qiudm
 * @date 2018/11/9 11:24
 * @desc
 */
public interface IWxMsgReplyConfigService {

    /**
     * 新增回复的配置
     * @param request
     * @param userId 创建者id
     */
    void saveWxReplyConfig(WxMsgReplyConfigRequest request, String userId);


    WxMsgReplyConfig getByReqText(String reqText);






}
