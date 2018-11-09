package com.codermi.blog.support.wx.data;

import lombok.Data;

/**
 * @author qiudm
 * @date 2018/11/8 18:01
 * @desc
 */
@Data
public class WxBaseMessage {
    /**
     * 	开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private int CreateTime;
    /**
     * text
     * @see com.codermi.blog.support.wx.enums.WxEnums.MsgType
     */
    private String MsgType;

}
