package com.api.security;

import com.api.business.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

public class AppSecurityContext implements SecurityContext {

    private Set<String> roles;
    private User user;
    private boolean isSecure;

    AppSecurityContext(Set<String> roles, final User user, boolean isSecure) {
        this.roles = roles;
        this.isSecure = isSecure;
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean isUserInRole(String role) {
        return roles.contains(role);
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return BASIC_AUTH;
    }
}
