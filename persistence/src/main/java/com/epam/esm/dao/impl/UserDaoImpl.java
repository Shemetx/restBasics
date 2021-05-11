package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.domain.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of UserDao
 */
@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }
}
