package com.codermi.blog.user.data.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import com.codermi.blog.user.enums.*;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/6/28 10:58
 * @desc
 */
@Document(collection = "t_user")
@Data
public class User {

    private String openId;

    private String userId;

    private String nickName;

    private String email;

    /**
     * 0-女 1-男 default-1
     */
    private Byte sex = 1;

    private String headPic;

    private String password;

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
