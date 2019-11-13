package com.api.security;

import com.api.business.User;

import javax.ws.rs.core.SecurityContext;
import java.util.HashSet;
import java.util.Set;

class AppSecurityContextFactory {

    private final User user;
    private final Set<String> roles;
    private final SecurityContext originalContext;

    AppSecurityContextFactory(SecurityContext originalContext, String login, String account) {
        this.originalContext = originalContext;
        this.user = new User(login, account); // Il faudra avoir l'entité/compte et le rôle du user dans le payload
        this.roles = new HashSet<>();
        roles.add("ADMIN");
    }

    AppSecurityContext build() {
        return new AppSecurityContext(roles, user, originalContext.isSecure());
    }
}
