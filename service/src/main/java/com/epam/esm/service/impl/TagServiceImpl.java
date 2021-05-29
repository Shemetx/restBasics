package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementation of TagService
 */
@Component
public class TagServiceImpl implements TagService {


    private TagDao tagDao;
    private UserDao userService;

    @Autowired
    public void setUserService(UserDao userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTagDataService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Page<Tag> findAll(int page, int size) {
        return tagDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Tag findById(Integer id) {
        Optional<Tag> byId = tagDao.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("Tag with id: '" + id + "' not found");
        }
        return byId.get();
    }

    @Override
    public Tag findByName(String name) {
        Optional<Tag> byName = tagDao.findByName(name);
        if (!byName.isPresent()) {
            throw new EntityNotFoundException("Tag with name: '" + name + "' not found");
        }
        return byName.get();
    }

    @Transactional
    @Override
    public Tag save(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Tag byId = findById(id);
        tagDao.delete(byId);
    }

    @Override
    public Tag findMostUsed() {
        Integer userIdWithMaxCost = userService.findUserIdWithMaxCost();
        return tagDao.findMostUsed(userIdWithMaxCost).get();
    }

}
