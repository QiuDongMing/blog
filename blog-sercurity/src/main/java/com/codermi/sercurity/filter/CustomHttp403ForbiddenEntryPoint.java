package com.codermi.sercurity.filter;

import com.codermi.common.base.enums.ErrorCode;
import com.codermi.sercurity.utils.HttpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/9/19 17:49
 * @desc
 */
public class CustomHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        try {
            HttpUtils.responseError(response, ErrorCode.NEED_LOGIN.getErrorCode(), ErrorCode.NEED_LOGIN.getErrMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
