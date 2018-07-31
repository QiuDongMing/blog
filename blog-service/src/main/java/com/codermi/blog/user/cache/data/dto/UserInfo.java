package com.codermi.blog.user.cache.data.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/20 11:42
 * @desc
 */
@Data
public class UserInfo implements Serializable {

    private String userId;

    private String nickName;

    private String email;
    /**
     * 0-女 1-男 default-1
     */
    private Byte sex = 1;

    private String headPic;

    private Long birthday;

    private List<String> roles;

}
