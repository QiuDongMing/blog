package com.codermi.blog.support.wx.data;

import lombok.Data;

/**
 * @author qiudm
 * @date 2018/11/8 18:14
 * @desc 微信文本消息
 */
@Data
public class WxTextMessage extends WxBaseMessage {

    private String Content;

}
