package com.codermi.sercurity.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/7/26 22:35
 * @desc
 */
public class ___JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        try {
//            String token = getToken(request);
//            if (token != null) {
//                UserInfo userInfo = JwtTokenUtil.getUserInfo(token);
//                if (Objects.nonNull(userInfo)) {
//                    //最关键的部分就是这里, 我们直接注入了Role信息
//                    List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
//                    List<String> roles = userInfo.getRoles();
//                    if (!CollectionUtils.isEmpty(roles)) {
//                        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
//                    }
//                    SecurityContextHolder.getContext().setAuthentication(
//                            new UsernamePasswordAuthenticationToken(
//                                    null, null, authorities));
//
////                    //保存认证信息到requestHeader
//                    response.setHeader("userID", userInfo.getUserId());
//
//                } else {
//                    //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                    responseError(response, HttpServletResponse.SC_UNAUTHORIZED, "授权失败");
//                    return;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
////            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
//            try {
//                responseError(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
//            } catch (Exception e1) {
//                e1.printStackTrace();
//                throw new ServiceException(e1.getMessage());
//            }
//            return;
//        }
//        filterChain.doFilter(request, response);
    }

//    private String getToken(HttpServletRequest request) {
//        String token = request.getHeader("access-token");
//        if (token == null) {
//            token = request.getHeader("access_token");
//        }
//        if (token == null) {
//            token = request.getHeader("accessToken");
//        }
//        return token;
//    }

//
//    private void responseError(HttpServletResponse response, int resultCode, String resultMsg) throws Exception {
//        JsonResult result = JsonResult.RESULT(resultCode, resultMsg, null);
//        String text = JSON.toJSONString(result);
//        response.setContentType("application/json; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(text);
//        response.getWriter().flush();
//    }




}
