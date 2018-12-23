package com.codermi.sercurity.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author qiudm
 * @date 2018/12/5 18:05
 * @desc
 */
public class AuthUser implements UserDetails {

    private final String userId;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enable;



    public AuthUser(String userId, boolean enable,  Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.authorities = authorities;
        this.enable = enable;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }


    public String getUserId() {
        return userId;
    }
}
