package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Implementation of TagService
 */
@Component
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    private PageUtil pageUtil;

    /**
     * Sets page util.
     *
     * @param pageUtil the page util
     */
    @Autowired
    public void setPageUtil(PageUtil pageUtil) {
        this.pageUtil = pageUtil;
    }

    /**
     * Sets tag dao.
     *
     * @param tagDao the tag dao
     */
    @Autowired
    public void setTagDao(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findAll(int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        return tagDao.findAll(page, size);
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
        Tag byName;
        try {
            byName = tagDao.findByName(name);
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Tag with name: '" + name + "' not found");
        }
        return byName;
    }

    @Transactional
    @Override
    public Tag save(Tag tag) {
        Tag save;
        try {
            save = tagDao.save(tag);
        } catch (PersistenceException e) {
            throw new EntityAlreadyExistsException("Tag with name: '" + tag.getName() + "' already exists");
        }
        return save;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Tag byId = findById(id);
        tagDao.delete(byId);
    }

    @Override
    public Tag findMostUsed() {
        return tagDao.findMostUsed();
    }

}
