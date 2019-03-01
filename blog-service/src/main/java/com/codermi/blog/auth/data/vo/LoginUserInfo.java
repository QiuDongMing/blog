package com.codermi.blog.auth.data.vo;

import com.codermi.blog.user.cache.data.dto.UserInfo;
import lombok.Data;

/**
 * @author qiudm
 * @date 2019/2/28 15:33
 * @desc
 */
@Data
public class LoginUserInfo extends UserInfo {

    private String password;

}
