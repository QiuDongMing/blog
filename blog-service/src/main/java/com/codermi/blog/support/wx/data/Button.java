package com.codermi.blog.support.wx.data;

import lombok.Data;

/**
 * @author qiudm
 * @date 2018/11/9 13:45
 * @desc
 */
@Data
public class Button {

    /**
     *  二级菜单数组，个数应为1~5个
     * 	否
     */
    private String sub_button;


    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;

    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;

    /**
     * 	菜单KEY值，用于消息接口推送，不超过128字节
     * 	是否必须： click等点击类型必须
     */
    private String key;

    /**
     * 链接，用户点击菜单可打开链接，不超过1024字节。
     * 是否必须：url	view、miniprogram类型必须	网页
     * type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;

    /**
     * 	调用新增永久素材接口返回的合法media_id
     * 	是否必须：media_id类型和view_limited类型必须
     */
    private String media_id;

    /**
     * 小程序的appid（仅认证公众号可配置）
     * 是否必须：miniprogram类型必须
     */
    private String appid;

    /**
     * 小程序的页面路径
     * miniprogram类型必须
     */
    private String pagepath;

}
