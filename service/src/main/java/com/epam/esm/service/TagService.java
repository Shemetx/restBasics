package com.epam.esm.service;

import com.epam.esm.dao.impl.TagsDao;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TagService {

    private TagsDao tagsDao;

    @Autowired
    public void setTagsDao(TagsDao tagsDao) {
        this.tagsDao = tagsDao;
    }

    public void save(Tag tag) {
        tagsDao.save(tag);
    }

    public void update(Tag tag) {
        tagsDao.update(tag);
    }

    public void delete(int id) {
        tagsDao.delete(id);
    }

    public List<Tag> findAll() {
        return tagsDao.index();
    }

    public Tag findById(int id) {
        Tag tag = tagsDao.findById(id);
        if(tag == null) {
            throw new EntityNotFoundException("Tag with id: '" + id + "' not found");
        }
        return tag;
    }

    public Tag findByName(String name) {
        Tag tag = tagsDao.findByName(name);
        if (tag == null) {
            throw new EntityNotFoundException("Tag: '" + name + "' not found");
        }
        return tag;
    }
}
