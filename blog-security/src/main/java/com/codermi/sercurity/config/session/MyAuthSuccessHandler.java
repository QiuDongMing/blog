package com.codermi.sercurity.config.session;

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
import java.util.Collection;

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
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println("JSON.toJSONString(authorities) = " + JSON.toJSONString(authorities));


        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("JSON.toJSONString(principal) = " + JSON.toJSONString(principal));

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonResult.SUCCESS("登录成功", null).toJSONString());
        out.flush();
        out.close();
    }
}
