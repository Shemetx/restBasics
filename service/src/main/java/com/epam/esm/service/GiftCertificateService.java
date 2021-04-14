package com.epam.esm.service;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;

import java.util.List;
import java.util.Set;

/**
 * The interface Gift certificate service.
 */
public interface GiftCertificateService {
    /**
     * Save.
     *
     * @param giftCertificate the gift certificate
     */
    void save(GiftCertificate giftCertificate);

    /**
     * Update.
     *
     * @param giftCertificate the gift certificate
     */
    void update(GiftCertificate giftCertificate);

    /**
     * Find by part of name list.
     *
     * @param name the name
     * @return the list
     */
    List<GiftCertificate> findByPartOfName(String name);

    /**
     * Find by part of description list.
     *
     * @param description the description
     * @return the list
     */
    List<GiftCertificate> findByPartOfDescription(String description);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(int id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<GiftCertificate> findAll();

    /**
     * Find all by tag.
     *
     * @param tag the tag
     * @return the list
     */
    List<GiftCertificate> findAllByTag(String tag);

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    GiftCertificate findById(int id);

    /**
     * Gets sorted list.
     *
     * @param sortType the sort type
     * @param sortBy   the sort by
     * @return the sorted list
     */
    List<GiftCertificate> getSortedList(String sortType, String sortBy);

    /**
     * Find certificate tags.
     *
     * @param id the id
     * @return the set
     */
    Set<Tag> findCertificateTags(int id);

    /**
     * Find by name gift certificate.
     *
     * @param name the name
     * @return the gift certificate
     */
    GiftCertificate findByName(String name);

    /**
     * Parse certificate tags.
     *
     * @param tags the tags
     * @param id   the id
     */
    void parseCertificateTags(Set<Tag> tags, int id);
}
