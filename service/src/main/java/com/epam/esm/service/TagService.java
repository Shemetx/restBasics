package com.epam.esm.service;

import com.epam.esm.dao.impl.TagsDaoImpl;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.specification.impl.FindAll;
import com.epam.esm.specification.impl.FindById;
import com.epam.esm.specification.impl.tags.FindByName;
import com.epam.esm.specification.impl.tags.UpdateTagById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TagService {

    private TagsDaoImpl tagsDao;

    @Autowired
    public void setTagsDao(TagsDaoImpl tagsDao) {
        this.tagsDao = tagsDao;
    }

    public void save(Tag tag) {
        tagsDao.add(tag);
    }

    public void update(Tag tag) {
         tagsDao.update(new UpdateTagById(tag));
    }

    public void delete(Tag tag) {
        tagsDao.delete(tag,new FindById(tag.getId()));
    }

    public List<Tag> findAll() {
        return new ArrayList<>(tagsDao.queryAll(new FindAll()));
    }

    public Tag findById(int id) {
        Optional<Tag> query = tagsDao.query(new FindById(id));
        if(!query.isPresent()) {
            throw new EntityNotFoundException("Tag with id: '" + id + "' not found");
        }
        return query.get();
    }

    public Tag findByName(String name) {
        Optional<Tag> tag = tagsDao.query(new FindByName(name));
        if (!tag.isPresent()) {
            throw new EntityNotFoundException("Tag: '" + name + "' not found");
        }
        return tag.get();
    }
}
