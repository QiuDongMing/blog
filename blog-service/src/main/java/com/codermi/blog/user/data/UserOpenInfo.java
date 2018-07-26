package com.codermi.blog.user.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiudm
 * @date 2018/7/26 15:42
 * @desc 用户的公开信息
 */
@Data
public class UserOpenInfo implements Serializable {

    private String openId;

    private String nickName;

    private String headPic;

    private Long birthday;

    private List<String> roles;

}
