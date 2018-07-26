package com.coderme.sercurity.filter;

import com.alibaba.fastjson.JSON;
import com.coderme.blog.user.data.UserOpenInfo;
import com.coderme.sercurity.utils.JwtTokenUtil;
import com.google.common.collect.Lists;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/7/26 22:35
 * @desc
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getToken(request);
            if (token != null) {
                UserOpenInfo userOpenInfo = JwtTokenUtil.getUserOpenInfo(token);
                if (Objects.nonNull(userOpenInfo)) {
                    //最关键的部分就是这里, 我们直接注入了Role信息
                    List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
                    List<String> roles = userOpenInfo.getRoles();
                    System.out.println("roles = " + JSON.toJSONString(roles));
                    if (!CollectionUtils.isEmpty(roles)) {
                        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
                    }
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    null, null, authorities));
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (token == null) {
            token = request.getHeader("access_token");
        }
        if (token == null) {
            token = request.getHeader("accessToken");
        }
        return token;
    }


}
