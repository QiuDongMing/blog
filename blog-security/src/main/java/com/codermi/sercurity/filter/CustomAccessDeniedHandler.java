package com.codermi.sercurity.filter;

import com.codermi.common.base.enums.ErrorCode;
import com.codermi.sercurity.utils.HttpUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/9/19 17:48
 * @desc
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {
        try {
            HttpUtils.responseError(response,ErrorCode.FORBIDDEN_ACCESS.getErrorCode(), ErrorCode.FORBIDDEN_ACCESS.getErrMsg() );
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}