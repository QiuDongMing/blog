package com.codermi.sercurity.config;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.enums.UserEnums.*;
import com.codermi.blog.user.service.IUserService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author qiudm
 * @date 2018/12/5 17:25
 * @desc
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserInfo userInfo = userService.getUserInfo(userId);
        System.out.println("JSON.toJSONString(userInfo) = " + JSON.toJSONString(userInfo));
        if (userInfo == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userId));
        } else {
            List<GrantedAuthority> grantedAuthorities = this.buildUserGrant(userInfo);
            System.out.println("JSON.toJSONString(grantedAuthorities) = " + JSON.toJSONString(grantedAuthorities));
            return new AuthUser(userId, Objects.equals(userInfo.getStatus(), UserStatus.NORMAL.getStatus()),  grantedAuthorities);
        }
    }

    public List<GrantedAuthority> buildUserGrant(UserInfo userInfo) {
        List<GrantedAuthority> userGrants = Lists.newArrayList();
        if (userInfo == null) return userGrants;
        Set<String> perms = userInfo.getPerms();
        if (CollectionUtils.isNotEmpty(perms)) {
            for (String perm : perms) {
                userGrants.add(new SimpleGrantedAuthority(perm));
            }
        }
        if (Objects.equals(userInfo.getUserType(), UserType.ADMIN.getType())) {
            userGrants.add(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()));
        }
        if (Objects.equals(userInfo.getUserType(), UserType.USER.getType())) {
            userGrants.add(new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
        }
        return userGrants;
    }

}
