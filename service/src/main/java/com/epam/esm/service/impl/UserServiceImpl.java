package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.impl.UserDaoImpl;
import com.epam.esm.domain.User;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of UserService.
 */
@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    /**
     * Sets user dao.
     *
     * @param userDao the user dao
     */
    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Integer id) {
        User byId = userDao.findById(id);
        if (byId == null) {
            throw new EntityNotFoundException("User with id: '" + id + "' not found");
        }
        return byId;
    }
}
