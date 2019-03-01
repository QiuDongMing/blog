package com.codermi.sercurity.config.session;

import com.codermi.blog.user.enums.UserEnums;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qiudm
 * @date 2019/2/28 18:26
 * @desc
 */
public class ExUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public static final String SPRING_SECURITY_FORM_USERTYPE_KEY = "userType";

    private String userTypeParameter = SPRING_SECURITY_FORM_USERTYPE_KEY;

    private boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        Integer userType = obtainUserType(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        if (userType == null) {
            userType = UserEnums.UserType.USER.getType();
        }

        username = username.trim();
        username = username + ":" + userType;

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    private Integer obtainUserType(HttpServletRequest request) {
        String userType = request.getParameter(userTypeParameter);
        userType = StringUtils.isEmpty(userType) ? String.valueOf(UserEnums.UserType.USER.getType()) : userType;
        return Integer.valueOf(userType);
    }


    public void setUserTypeParameter(String userTypeParameter) {
        Assert.hasText(userTypeParameter, "userType parameter must not be empty or null");
        this.userTypeParameter = userTypeParameter;
    }


    public String getUserTypeParameter() {
        return userTypeParameter;
    }
}
