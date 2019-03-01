package com.codermi.sercurity.config;

import com.alibaba.fastjson.JSON;
import com.codermi.common.base.utils.JsonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qiudm
 * @date 2018/12/5 10:14
 * @desc 登录成功后的处理
 */
@Component
public class MyAuthSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
//                .getContext().getAuthentication().getAuthorities();
//        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonResult.SUCCESS("登录成功", null).toJSONString());
        out.flush();
        out.close();
    }
}
