package com.codermi.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author qiudm
 * @date 2018/7/25 9:56
 * @desc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthenticationFilter authenticationFilter;

//
//    @Autowired
//    private MyAccessDeniedHandler myAccessDeniedHandler;
//
//    @Autowired
//    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAuthenticationEntryPoint unauthorizedHandler;


    @Bean
    public MyAuthFilter myAuthFilter() throws Exception {
        return new MyAuthFilter();
    }

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    private static final String[] AUTH_WHITE_LIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/**/*.html",
            //--公开的无需登录的
            "/**/open/**"
    };

    /**
     *
     * @param httpSecurity
     * @throws Exception
     * 通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。
     *   1、http.authorizeRequests()方法有多个子节点，每个macher按照他们的声明顺序执行。
     *   2、我们指定任何用户都可以访问的多个URL模式。任何用户都可以访问URL以 "/resources/",开头的URL ,以及"/signup", "/about".
     *   3、以"/admin/" 开头的URL只能由拥有 "ROLEADMIN"角色的用户访问. 请注意我们使用HasRole方法，没有使用ROLE前缀。
     *   4、任何以/db/开头的URL需要用户同时具有"ROLEADMIN" 和 "ROLE_DBA". 和上面一样我们的hasRole方法也没有使用ROLE前缀。
     *   5、尚未匹配的任何URL要求用户进行身份验证
     *
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/user/login", "/user/register").permitAll()
                .antMatchers(AUTH_WHITE_LIST).permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and()

                .addFilterBefore(myAuthFilter(), UsernamePasswordAuthenticationFilter.class);

    }


}
