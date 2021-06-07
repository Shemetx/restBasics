package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.domain.User;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of UserService.
 */
@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Sets user dao.
     *
     * @param userDao the user dao
     */
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> byId = userDao.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("User with id: '" + id + "' not found");
        }
        return byId.get();
    }


    @Override
    public User signUp(User user) {
        Optional<User> byEmailOrUsername = userDao.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if (byEmailOrUsername.isPresent()) {
            throw new EntityAlreadyExistsException("User with this email or username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> byUsername = userDao.findByUsername(username);
        if (!byUsername.isPresent()) {
            throw new EntityNotFoundException("User with email: '" + username + "' not found");

        }
        return byUsername.get();
    }
}
