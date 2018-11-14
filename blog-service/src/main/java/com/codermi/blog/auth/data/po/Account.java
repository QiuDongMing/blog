package com.codermi.blog.auth.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import com.codermi.blog.auth.enums.AccountEnum.*;
import com.codermi.blog.user.enums.UserEnums;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/11/12 18:30
 * @desc 账户
 */
@Document(collection = "t_auth_account")
@Data
public class Account extends BaseInfo {
    /**
     * 账户id
     */
    private String accountId;

    /**
     * 账户类型
     * @see AccountType
     */
    private String accountType;

    /**
     * 手机号、微信号、email
     */
    private String accountNum;

    /**
     * 账户密码
     */
    private String password;

    /**
     * 系统用户、普通用户
     * @see UserEnums.UserType
     */
    private Integer userType;

}
