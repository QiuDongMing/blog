package com.codermi.blog.common.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qiudm
 * @date 2018/7/31 17:25
 * @desc
 */
@Component
public class ReqUtil implements InitializingBean {

    public static ReqUtil instance;

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }


    public HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public String getUserId() {
        return getRequest().getHeader("userID");
    }

}
