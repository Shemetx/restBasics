package com.epam.esm.service;

import com.epam.esm.domain.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The interface Tag service.
 */
public interface TagService {

    /**
     * Find all list.
     *
     * @param page the page
     * @param size the size
     * @return the list
     */
    Page<Tag> findAll(int page, int size);

    /**
     * Find by id tag.
     *
     * @param id the id
     * @return the tag
     */
    Tag findById(Integer id);

    /**
     * Find by name tag.
     *
     * @param name the name
     * @return the tag
     */
    Tag findByName(String name);

    /**
     * Save tag.
     *
     * @param tag the tag
     * @return the tag
     */
    Tag save(Tag tag);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Find most used tag.
     *
     * @return the tag
     */
    Tag findMostUsed();
}
