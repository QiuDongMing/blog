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
    private final String password;
    private final Integer userType;


    public AuthUser(String userId,
                    boolean enable,
                    String password,
                    Integer userType,
                    Collection<? extends GrantedAuthority>authorities
    ) {

        this.userId = userId;
        this.userType = userType;
        this.authorities = authorities;
        this.enable = enable;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }


    public String getUserId() {
        return userId;
    }
}
