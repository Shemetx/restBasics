package com.epam.esm.service;

import com.epam.esm.domain.Tag;

import java.util.List;


public interface TagService {
    List<Tag> findAll();
    Tag findById(Integer id);
    Tag findByName(String name);
    Tag save(Tag tag);
    void delete(Integer id);
}
