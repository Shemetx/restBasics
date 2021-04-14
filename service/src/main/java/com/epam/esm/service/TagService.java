package com.epam.esm.service;

import com.epam.esm.domain.Tag;

import java.util.List;

/**
 * The interface Tag service.
 */
public interface TagService {
    /**
     * Saves tag
     *
     * @param tag the tag
     */
    void save(Tag tag);

    /**
     * Delete tag
     *
     * @param id the id
     */
    void delete(int id);

    /**
     * Find all tags list.
     *
     * @return the list
     */
    List<Tag> findAll();

    /**
     * Find all certificate tags
     *
     * @param id the id
     * @return the list
     */
    List<Tag> findCertificateTags(int id);

    /**
     * Find tag by id
     *
     * @param id the id
     * @return the tag
     */
    Tag findById(int id);

    /**
     * Find tag by name
     *
     * @param name the name
     * @return the tag
     */
    Tag findByName(String name);

}
