package com.coderme.blog.user.data.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/6/28 10:58
 * @desc
 */
@Document(collection = "t_user")
@Data
public class User {

    private String userId;

    private String nickName;

    private String headPic;

    private String password;

    private Long birthday;

    private Long createTime;

}
