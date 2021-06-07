package com.epam.esm.jwt;

import com.epam.esm.domain.User;
import com.epam.esm.jwt.JwtUserFactory;
import com.epam.esm.service.UserService;
import com.epam.esm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Find user by username to work with.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserService userService;

    /**
     * Sets user service.
     *
     * @param userService the user service
     */
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userService.findByUsername(username);
        return JwtUserFactory.create(byUsername);
    }
}
