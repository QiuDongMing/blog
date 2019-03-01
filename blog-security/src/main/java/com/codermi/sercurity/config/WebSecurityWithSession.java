package com.codermi.sercurity.config;

import com.codermi.blog.common.constants.Constants;
import com.codermi.common.base.utils.EncryUtils;
import com.codermi.sercurity.filter.ExUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

/**
 * @author qiudm
 * @date 2019/2/28 13:54
 * @desc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityWithSession extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private MyAuthFailHandler authenticationFailHandler;

    @Autowired
    private MyAuthSuccessHandler myAuthSuccessHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/user/login_p")
                .loginProcessingUrl("/login")
                .successHandler(myAuthSuccessHandler)
                .failureHandler(authenticationFailHandler)
                .permitAll()

                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler)
                .permitAll()

                .and()
                .sessionManagement().maximumSessions(1);


        httpSecurity
                .addFilterBefore(exUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .csrf().disable();

    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public ExUsernamePasswordAuthenticationFilter exUsernamePasswordAuthenticationFilter() throws Exception {
        ExUsernamePasswordAuthenticationFilter filter = new ExUsernamePasswordAuthenticationFilter();

        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(myAuthSuccessHandler);
        return filter;
    }


    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("auth = " + auth);
        auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return EncryUtils.md5Hex((String) rawPassword, Constants.PASSWORD_SALT);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return Objects.equals(EncryUtils.md5Hex((String) rawPassword, Constants.PASSWORD_SALT), encodedPassword);
            }
        });
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITE_LIST);
    }


    private static final String[] AUTH_WHITE_LIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/**/webjars/**",
            "/**/swagger*/**",
            "/v2/api-docs",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            //--公开的无需登录的
            "/**/open/**",
            "/static/**",
            "/user/login_p",
            "/user/register"
    };


}
