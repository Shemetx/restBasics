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
}
