package com.epam.esm.dao;

import com.epam.esm.domain.Tag;

import java.util.List;


public interface TagDao {
    List<Tag> findAll();
    Tag findByName(String name);
    Tag findById(Integer id);
    void save(Tag tag);
    void delete(Tag tag);
}
