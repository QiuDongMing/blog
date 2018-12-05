package com.codermi.sercurity.config;

import com.codermi.blog.user.cache.data.dto.UserInfo;
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
        if (userInfo == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userId));
        } else {
            return new AuthUser(userId, this.buildUserGrant(userInfo));
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
        List<String> roleIds = userInfo.getRoleIds();
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (String role : roleIds) {
                userGrants.add(new SimpleGrantedAuthority(role));
            }
        }

        return userGrants;
    }


}
