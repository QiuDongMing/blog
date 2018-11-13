package com.codermi.blog.user.data.base;

import lombok.Data;


/**
 * @author qiudm
 * @date 2018/9/29 18:21
 * @desc
 */
@Data
public class UserBaseInfo {

    private String userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 0-女 1-男 default-1
     */
    private Byte sex = 1;

    private String headPic;

}
