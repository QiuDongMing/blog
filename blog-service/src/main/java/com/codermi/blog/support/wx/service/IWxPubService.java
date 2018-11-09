package com.codermi.blog.support.wx.service;

import com.codermi.blog.support.wx.data.WxButton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qiudm
 * @date 2018/11/7 18:55
 * @desc
 */
public interface IWxPubService {

//    /**
//     * 获取全局的唯一接口调用凭据
//     * @return
//     */
//    protected String getPubAccessToken();

    /**
     * 处理公众号微信发布者发送的消息和事件
     */
    void handlerMsgEvent(HttpServletRequest request, HttpServletResponse response);

    /**
     * 新增自定义菜单
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
     * @param wxButton
     */
    void addMenu(WxButton wxButton);




}
