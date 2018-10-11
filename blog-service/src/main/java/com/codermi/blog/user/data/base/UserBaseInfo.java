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

    private String nickName;

    /**
     * 0-女 1-男 default-1
     */
    private Byte sex = 1;

    private String headPic;



}
