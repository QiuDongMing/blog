package com.codermi.blog.user.cache.data.dto;


import lombok.Data;
import com.codermi.blog.user.enums.*;
/**
 * @author qiudm
 * @date 2018/9/6 15:40
 * @desc
 */
@Data
public class AccessToken {

    private String userId;

    private String token;

    private String openId;

    /**
     * @see UserEnums.UserType
     */
    private Integer userType;

}
