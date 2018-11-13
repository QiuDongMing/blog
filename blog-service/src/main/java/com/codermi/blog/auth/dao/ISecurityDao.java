package com.codermi.blog.auth.dao;

import com.codermi.blog.auth.data.po.Account;

/**
 * @author qiudm
 * @date 2018/11/13 14:26
 * @desc
 */
public interface ISecurityDao {


    Account getByAccountNumAndUserType(String accountNum, Integer userType);


    void insertAccount(Account account);


}
