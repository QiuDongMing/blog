package com.codermi.sercurity.utils;

import com.codermi.sercurity.config.AuthUser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author qiudm
 * @date 2019/3/1 11:45
 * @desc
 */
@Component
public class ReqUtil implements InitializingBean {

    public static ReqUtil instance;


    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    public AuthUser getAuthUser() {
        return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public String getUserId() {
        String userId = null;
        AuthUser authUser = getAuthUser();
        if(authUser != null) {
            userId = authUser.getUserId();
        }
        return userId;
    }



}
