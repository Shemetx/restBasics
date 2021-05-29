package com.epam.esm.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * User domain which connects with jwt security
 */
public class JwtUser implements UserDetails {

    private final Integer id;
    private final String firstName;
    private final String username;
    private final String email;
    private final String password;
    private final boolean enable;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Instantiates a new Jwt user.
     *
     * @param id          the id
     * @param firstName   the first name
     * @param username    the username
     * @param email       the email
     * @param password    the password
     * @param enable      the enable
     * @param authorities the authorities
     */
    public JwtUser(Integer id, String firstName, String username, String email,
                   String password, boolean enable,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.authorities = authorities;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
        return username;
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
}
