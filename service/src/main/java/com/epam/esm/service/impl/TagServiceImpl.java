package com.epam.esm.service.impl;

import com.epam.esm.dao.TagsDao;
import com.epam.esm.dao.impl.TagsDaoImpl;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * implementation of Tag service to connect controller with dao layer
 */
@Component
public class TagServiceImpl implements TagService {

    private TagsDao tagsDaoImpl;

    /**
     * Sets tags dao.
     *
     * @param tagsDaoImpl the tags dao
     */
    @Autowired
    public void setTagsDao(TagsDaoImpl tagsDaoImpl) {
        this.tagsDaoImpl = tagsDaoImpl;
    }

    /**
     * Saves tag
     *
     * @param tag the tag
     */
    public void save(Tag tag) {
        try {
        tagsDaoImpl.save(tag);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Tag with name: '" +tag.getName() +"' already exists" );
        }
    }

    /**
     * Deletes tag
     *
     * @param id the id
     */
    public void delete(int id) {
        tagsDaoImpl.delete(id);
    }

    /**
     * Find all tags
     *
     * @return the list
     */
    public List<Tag> findAll() {
        return tagsDaoImpl.index();
    }

    /**
     * Find all certificate tags
     *
     * @param id the id
     * @return the list
     */
    public List<Tag> findCertificateTags(int id) {
        return tagsDaoImpl.findCertificateTags(id);
    }

    /**
     * Find tag by id
     *
     * @param id the id
     * @return the tag
     */
    public Tag findById(int id) {
        Tag tag = tagsDaoImpl.findById(id);
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
        Tag tag = tagsDaoImpl.findByName(name);
        if (tag == null) {
            throw new EntityNotFoundException("Tag: '" + name + "' not found");
        }
        return tag;
    }
}
