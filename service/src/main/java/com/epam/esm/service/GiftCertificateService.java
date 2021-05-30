package com.epam.esm.service;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * The interface Gift certificate service.
 */
public interface GiftCertificateService {

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Save gift certificate.
     *
     * @param giftCertificate the gift certificate
     * @return the gift certificate
     */
    GiftCertificate save(GiftCertificate giftCertificate);

    /**
     * Find all certificates.
     *
     * @param page the page
     * @param size the size
     * @return the list
     */
    Page<GiftCertificate> findAll(Specification<GiftCertificate> specification, int page, int size);

    /**
     * Gets sorted certificates.
     *
     * @param sortType the sort type
     * @param sortBy   the sort by
     * @param page     the page
     * @param size     the size
     * @return the sorted list
     */
    Page<GiftCertificate> getSortedList(Specification<GiftCertificate> specification,
                                        String sortType, String sortBy, int page, int size);

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    GiftCertificate findById(Integer id);


    /**
     * Find all certificates by tags.
     *
     * @param tags the tags
     * @param page the page
     * @param size the size
     * @return the list
     */
    Page<GiftCertificate> findAllByTags(List<String> tags, int page, int size);

    /**
     * Update.
     *
     * @param giftCertificate the gift certificate
     */
    void update(GiftCertificate giftCertificate);
}
