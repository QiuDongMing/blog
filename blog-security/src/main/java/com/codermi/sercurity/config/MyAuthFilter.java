package com.codermi.sercurity.config;

import com.codermi.blog.auth.service.ISecurityService;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/12/5 18:15
 * @desc
 */
public class MyAuthFilter extends OncePerRequestFilter {

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = this.getToken(request);
        if (token != null) {
            AccessToken accessToken = securityService.getAccessToken(token);
            if (accessToken != null) {
                String userId = accessToken.getUserId();
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        request));
                logger.info("authenticated userId " + userId + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 获取请求头的token
     * @param request
     * @return
     */
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
