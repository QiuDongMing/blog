package com.codermi.blog.user.cache.data.dto;

import com.codermi.blog.user.enums.UserEnum;
import lombok.Data;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/20 11:42
 * @desc
 */
@Data
public class UserInfo {

    private String openId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;

    private String email;

    /**
     * 0-女 1-男 default-1
     */
    private Byte sex = 1;

    private String headPic;

    private Long birthday;

    private Long createTime;

    private List<String> roleIds;

    /**
     * 保存权限列表
     */
    private List<String> perms;

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
