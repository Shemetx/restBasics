package com.epam.esm.security.jwt;

import com.epam.esm.domain.Role;
import com.epam.esm.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                true,
                mapToGrantedAuthority(user.getRole())
        );
    }

    private static Set<GrantedAuthority> mapToGrantedAuthority(Set<Role> roles) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toSet());
    }
}
