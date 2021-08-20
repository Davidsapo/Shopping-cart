package com.shopping.cart.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.shopping.cart.enums.Permissions.*;

public enum Role {

    USER(new Permissions[]{READ_PRODUCTS, MANAGE_CART, MANAGE_PROFILE}),
    ADMIN(new Permissions[]{READ_PRODUCTS, MANAGE_PRODUCTS, READ_USERS, MANAGE_USERS});

    Role(Permissions[] permissions) {
        this.permissions = permissions;
    }

    private final Permissions[] permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = Arrays
                .stream(permissions)
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
