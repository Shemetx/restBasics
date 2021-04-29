package com.epam.esm.dao;

import com.epam.esm.domain.Tag;

import java.util.List;


/**
 * The interface Tag dao.
 */
public interface TagDao {
    /**
     * Find all tags.
     *
     * @param page the page
     * @param size the size
     * @return the list of all tags
     */
    List<Tag> findAll(int page, int size);

    /**
     * Find by name tag.
     *
     * @param name the name
     * @return the tag
     */
    Tag findByName(String name);

    /**
     * Find by id tag.
     *
     * @param id the id
     * @return the tag
     */
    Tag findById(Integer id);

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
     * @param tag the tag
     */
    void delete(Tag tag);

    /**
     * Find most used tag.
     *
     * @return the tag
     */
    Tag findMostUsed();
}
