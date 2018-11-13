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

    /**
     * 用户的账户id
     */
    private String accountId;

    private String openId;

    private String userId;

    /**
     * 用户名-唯一标识
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

    //private String password;

    private Long birthday;

    private Long createTime;

    private List<String> roleIds;

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
