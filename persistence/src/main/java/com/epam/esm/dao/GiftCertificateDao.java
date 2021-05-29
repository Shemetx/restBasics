package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface Gift certificate dao.
 */
public interface GiftCertificateDao extends JpaRepository<GiftCertificate, Integer>, GiftCertificateCustomDao {

    Page<GiftCertificate> findAll(Pageable pageable);

    /**
     * Find by part of name.
     *
     * @param name     the name
     * @param pageable the pageable
     * @return the page
     */
    Page<GiftCertificate> findByNameContains(String name, Pageable pageable);

    /**
     * Find by part of description.
     *
     * @param description the description
     * @param pageable    the pageable
     * @return the page
     */
    Page<GiftCertificate> findByDescriptionContains(String description, Pageable pageable);

}
