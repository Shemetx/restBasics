package com.epam.esm.dao;

import com.epam.esm.domain.Tag;

import java.util.List;

/**
 * The interface Tags dao.
 */
public interface TagsDao {
    /**
     * Returns all tags
     *
     * @return the list
     */
    List<Tag> index();

    /**
     * Find tag by tag id
     *
     * @param id the id
     * @return the tag
     */
    Tag findById(int id);

    /**
     * Find tag by tag name.
     *
     * @param name the name
     * @return the tag
     */
    Tag findByName(String name);

    /**
     * Find certificate tags list.
     *
     * @param id the id
     * @return the list
     */
    List<Tag> findCertificateTags(int id);

    /**
     * Save tag.
     *
     * @param tag the tag
     */
    void save(Tag tag);

    /**
     * Delete tag by id.
     *
     * @param id the id
     */
    void delete(int id);
}
