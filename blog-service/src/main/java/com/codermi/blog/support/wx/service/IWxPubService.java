package com.codermi.blog.support.wx.service;

import com.codermi.blog.support.wx.data.WxButton;
import com.codermi.blog.support.wx.data.WxMenu;
import com.codermi.blog.support.wx.data.WxUserInfo;

import javax.servlet.http.HttpServletRequest;

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
    String handlerMsgEvent(HttpServletRequest request);

    /**
     * 新增自定义菜单
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
     * @param wxButton
     */
    void addMenu(WxButton wxButton);

    /**
     * 获取微信菜单
     * @return
     */
    WxMenu getMenu();

    /**
     * 删除自定义菜单
     */
    void delMenu();

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    WxUserInfo getUserInfo(String openId);




}
