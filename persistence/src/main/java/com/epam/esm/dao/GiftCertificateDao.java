package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GiftCertificateDao extends JpaRepository<GiftCertificate, Integer>, GiftCertificateCustomDao {

    Page<GiftCertificate> findAll(Pageable pageable);

    Page<GiftCertificate> findByNameContains(String name, Pageable pageable);

    Page<GiftCertificate> findByDescriptionContains(String description, Pageable pageable);

}
