package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;

import java.util.List;

/**
 * The interface Gift certificate dao.
 */
public interface GiftCertificateDao {
    /**
     * Returns all certificates from database
     *
     * @return the list
     */
    List<GiftCertificate> index();

    /**
     * Save.
     *
     * @param cert the cert
     */
    void save(GiftCertificate cert);

    /**
     * Update.
     *
     * @param cert the cert
     */
    void update(GiftCertificate cert);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(int id);

    /**
     * Find by gift certificate id.
     *
     * @param id the id
     * @return the gift certificate
     */
    GiftCertificate findById(int id);

    /**
     * Find by gift certificate name.
     *
     * @param name the name
     * @return the gift certificate
     */
    GiftCertificate findByName(String name);

    /**
     * Find all by tag id.
     *
     * @param id the id
     * @return the list
     */
    List<GiftCertificate> findByTagId(int id);

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
}
