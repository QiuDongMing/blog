package com.codermi.sercurity.filter;

import com.codermi.common.base.enums.ErrorCode;
import com.codermi.sercurity.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/9/19 17:48
 * @desc 暂时弃用
 */
@Deprecated
//@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {

        try {
            HttpUtils.responseError(response,ErrorCode.FORBIDDEN_ACCESS.getErrorCode(), ErrorCode.FORBIDDEN_ACCESS.getErrMsg() );
        } catch (Exception e1) {
            LOGGER.error(e1.getMessage(), e1);
        }
    }

}