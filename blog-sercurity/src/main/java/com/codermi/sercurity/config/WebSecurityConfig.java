package com.codermi.sercurity.config;

import com.codermi.sercurity.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author qiudm
 * @date 2018/7/25 9:56
 * @desc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITE_LIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };


    /**
     *
     * @param http
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
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()  //1
//                .antMatchers("/resources/**", "/signup", "/about").permitAll() //2
//                .antMatchers("/admin/**").hasRole("ADMIN") //3
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")//4
//                .anyRequest().authenticated()   //5
//                .and()
//                // ...
//                .formLogin();

        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login", "/user/register").permitAll()
                .antMatchers(AUTH_WHITE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }





}
