package com.epam.esm.dao;

import com.epam.esm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * The interface User dao.
 */
public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);
}
