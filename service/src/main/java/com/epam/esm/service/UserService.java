package com.epam.esm.service;

import com.epam.esm.domain.User;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(Integer id);

    /**
     * Sign up user.
     *
     * @param user the user
     * @return the user
     */
    User signUp(User user);

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}
