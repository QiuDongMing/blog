package com.codermi.sercurity.filter;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.service.ISecurityService;
import com.codermi.blog.user.utils.AccessTokenCacheUtil;
import com.codermi.common.base.enums.ErrorCode;
import com.codermi.common.base.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/9/10 14:37
 * @desc
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private ISecurityService securityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);
        validateAccessToken(token, response);
    }

    private AccessToken validateAccessToken(String token, HttpServletResponse response) {
        AccessToken accessToken = null;
        try {
            accessToken = securityService.getAccessToken(token);
            if (accessToken == null) {
                throw new ServiceException(ErrorCode.INVALID_TOKEN);
            }

        } catch (ServiceException e) {
            LOGGER.error("accessToken error:", e);
            try {
                responseError(response, e.getResultCode(), e.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            LOGGER.error("error validateAccessToken:", e);
        }

        if (accessToken != null) {
            response.setHeader("userID", accessToken.getUserId());
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




}
