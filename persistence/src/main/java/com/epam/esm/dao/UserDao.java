package com.epam.esm.dao;

import com.epam.esm.domain.User;


/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(Integer id);
}
