package com.codermi.sercurity.filter;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.service.ISecurityService;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.utils.AccessTokenCacheUtil;
import com.codermi.common.base.enums.ErrorCode;
import com.codermi.common.base.utils.JsonResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
 * @date 2018/9/10 14:37
 * @desc
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private IUserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        filterUrl(request);

        String token = getToken(request);
        try {
            if (token != null) {
                AccessToken accessToken = validateAccessToken(token, response);
                UserInfo userInfo = userService.getUserInfo(accessToken.getUserId());
                if (Objects.nonNull(userInfo)) {
                    //最关键的部分就是这里, 我们直接注入了Role信息
                    List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
                    List<String> roles = userInfo.getRoles();
                    if (!CollectionUtils.isEmpty(roles)) {
                        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
                    }
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    null, null, authorities));

                    //保存认证信息到requestHeader
                    response.setHeader("userID", userInfo.getUserId());
                } else {
                    responseError(response, HttpServletResponse.SC_UNAUTHORIZED, "授权失败");
                    return;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            try {
                responseError(response, e.getResultCode(), e.getMessage());
                return;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                responseError(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new ServiceException(e1.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    private AccessToken validateAccessToken(String token, HttpServletResponse response) {
        AccessToken accessToken = securityService.getAccessToken(token);
        if (accessToken == null) {
            throw new ServiceException(ErrorCode.INVALID_TOKEN);
        }
        return accessToken;
    }


    private void responseError(HttpServletResponse response, int resultCode, String resultMsg) throws Exception {
        JsonResult result = JsonResult.RESULT(resultCode, resultMsg, null);
        String text = JSON.toJSONString(result);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(text);
        response.getWriter().flush();
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


    private boolean filterUrl(HttpServletRequest request) {
        boolean filter = false;

        String uri = request.getRequestURI();

        return filter;
    }




}
