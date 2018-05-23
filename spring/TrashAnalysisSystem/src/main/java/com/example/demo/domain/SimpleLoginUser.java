package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {
    private com.example.demo.domain.User user;
    // In default, all users are not admin
    private static final boolean isAdmin = false;

    public User getUser() {
        return this.user;
    }

    public SimpleLoginUser(com.example.demo.domain.User user) {
        super(user.getEmail(), user.getPassword(), determineRoles(isAdmin));
        this.user = user;
    }

    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
        return isAdmin ? ADMIN_ROLES : USER_ROLES;
    }

    @Override
    public String toString() {
        return this.user.toString();
    }
}
