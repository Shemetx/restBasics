package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;

import java.util.List;


/**
 * The interface Gift certificate dao.
 */
public interface GiftCertificateDao {
    /**
     * Find by order by name asc certificates.
     *
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByOrderByNameAsc(int page, int size);

    /**
     * Find by order by name desc certificates.
     *
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByOrderByNameDesc(int page, int size);

    /**
     * Find by order by create date asc certificates.
     *
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByOrderByCreateDateAsc(int page, int size);

    /**
     * Find by order by create date desc certificates.
     *
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByOrderByCreateDateDesc(int page, int size);

    /**
     * Find by part of name certificates.
     *
     * @param name the name
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByPartOfName(String name, int page, int size);

    /**
     * Find by part of description certificates.
     *
     * @param description the description
     * @param page        the page
     * @param size        the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByPartOfDescription(String description, int page, int size);

    /**
     * Find by tags certificates.
     *
     * @param tags the tags
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findByTags(List<Tag> tags, int page, int size);

    /**
     * Find all certificates.
     *
     * @param page the page
     * @param size the size
     * @return the list of all certificates
     */
    List<GiftCertificate> findAll(int page, int size);

    /**
     * Save gift certificate.
     *
     * @param giftCertificate the gift certificate
     * @return the gift certificate
     */
    GiftCertificate save(GiftCertificate giftCertificate);

    /**
     * Delete.
     *
     * @param giftCertificate the gift certificate
     */
    void delete(GiftCertificate giftCertificate);

    /**
     * Update.
     *
     * @param oldCert the old cert
     * @param newCert the new cert
     */
    void update(GiftCertificate oldCert, GiftCertificate newCert);

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    GiftCertificate findById(Integer id);
}
