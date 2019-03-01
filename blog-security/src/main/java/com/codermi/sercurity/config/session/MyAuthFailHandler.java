package com.codermi.sercurity.config.session;

import com.codermi.common.base.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qiudm
 * @date 2018/12/4 17:53
 * @desc
 */
@Component
public class MyAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthFailHandler.class);


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        LOGGER.error(exception.getMessage(), exception);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String errMsg = "登录失败";
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            errMsg = "用户名或密码错误";
        }

        out.write(JsonResult.FAILURE(errMsg).toJSONString());
        out.flush();
        out.close();
    }

}
