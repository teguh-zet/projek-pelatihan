package com.salim.systempub.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.salim.systempub.models.Auth;

public class CostumUserDetails implements UserDetails {

    private Auth auth;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(auth.getRole());
        return Collections.singleton(new SimpleGrantedAuthority(auth.getRole()));
    }
    
    public CostumUserDetails(Auth auth) {
        super();
        this.auth = auth;
    }

    @Override
    public String getPassword() {
        return auth.getPassword();
    }

    @Override
    public String getUsername() {
        return auth.getUsername();
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
        return true;
    }
    
}
