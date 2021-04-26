package com.epam.esm.dao;

import com.epam.esm.domain.Tag;

import java.util.List;


public interface TagDao {
    List<Tag> findAll(int page,int size);
    Tag findByName(String name);
    Tag findById(Integer id);
    void save(Tag tag);
    void delete(Tag tag);
    Tag findMostUsed();
}
