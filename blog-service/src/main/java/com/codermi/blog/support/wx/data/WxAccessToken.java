package com.codermi.blog.support.wx.data;

import lombok.Data;

/**
 * @author qiudm
 * @date 2018/11/8 14:38
 * @desc
 */
@Data
public class WxAccessToken {

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private long expires_in;

}
