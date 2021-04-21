package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Component
public class TagServiceImpl implements TagService {

    private TagDao tagDao;

    @Autowired
    public void setTagDao(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public Tag findById(Integer id) {
        Tag byId = tagDao.findById(id);
        if (byId == null) {
            throw new EntityNotFoundException("Tag with id: '" + id + "' not found");
        }
        return byId;
    }

    @Override
    public Tag findByName(String name) {
       Tag byName = tagDao.findByName(name);
        if (byName == null) {
            throw new EntityNotFoundException("Tag with name: '" + name + "' not found");
        }
        return byName;
    }

    @Transactional
    @Override
    public Tag save(Tag tag) {
        try {
            tagDao.save(tag);
        } catch (
                Exception e) {
            throw new EntityAlreadyExistsException("Tag with name: '" + tag.getName() + "' already exists");
        }
        return tag;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Tag byId = findById(id);
        tagDao.delete(byId);
    }

}
