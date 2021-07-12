package com.epam.esm.jwt;

import com.epam.esm.domain.Role;
import com.epam.esm.domain.User;
import com.epam.esm.jwt.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory creates JwtUser entity
 */
public final class JwtUserFactory {

    /**
     * Private constructor for utility class
     */
    private JwtUserFactory() {
    }

    /**
     * Create jwt user.
     *
     * @param user the user
     * @return the jwt user
     */
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
