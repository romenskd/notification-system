package com.github.romenskd.notificationgateway.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthentication extends AbstractAuthenticationToken {
    private final String userId;

    public JwtAuthentication(String userId) {
        super(Collections.singleton(new SimpleGrantedAuthority("USER")));
        this.userId = userId;
        setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}
