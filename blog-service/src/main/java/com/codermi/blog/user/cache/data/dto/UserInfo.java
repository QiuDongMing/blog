package com.codermi.blog.user.cache.data.dto;

import com.codermi.blog.user.enums.UserEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/20 11:42
 * @desc
 */
@Data
public class UserInfo {

    private String openId;

    private String userId;

    private String nickName;

    private String email;

    /**
     * 0-女 1-男 default-1
     */
    private Byte sex = 1;

    private String headPic;

    private Long birthday;

    private Long createTime;

    private List<String> roles;

    /**
     * 用户状态
     * @see UserEnum.UserStatus
     */
    private Integer status = 1;

    /**
     * 用户类型
     * @see UserEnum.UserType
     */
    private Integer userType;
}
