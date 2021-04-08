package com.epam.esm.service;

import com.epam.esm.dao.TagsDao;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Tag service to connect controller with dao layer
 */
@Component
public class TagService {

    private TagsDao tagsDao;

    /**
     * Sets tags dao.
     *
     * @param tagsDao the tags dao
     */
    @Autowired
    public void setTagsDao(TagsDao tagsDao) {
        this.tagsDao = tagsDao;
    }

    /**
     * Saves tag
     *
     * @param tag the tag
     */
    public void save(Tag tag) {
        tagsDao.save(tag);
    }

    /**
     * Deletes tag
     *
     * @param id the id
     */
    public void delete(int id) {
        tagsDao.delete(id);
    }

    /**
     * Find all tags
     *
     * @return the list
     */
    public List<Tag> findAll() {
        return tagsDao.index();
    }

    /**
     * Find all certificate tags
     *
     * @param id the id
     * @return the list
     */
    public List<Tag> findCertificateTags(int id) {
        return tagsDao.findCertificateTags(id);
    }

    /**
     * Find tag by id
     *
     * @param id the id
     * @return the tag
     */
    public Tag findById(int id) {
        Tag tag = tagsDao.findById(id);
        if (tag == null) {
            throw new EntityNotFoundException("Tag with id: '" + id + "' not found");
        }
        return tag;
    }

    /**
     * Find tag by name
     *
     * @param name the name
     * @return the tag
     */
    public Tag findByName(String name) {
        Tag tag = tagsDao.findByName(name);
        if (tag == null) {
            throw new EntityNotFoundException("Tag: '" + name + "' not found");
        }
        return tag;
    }
}
