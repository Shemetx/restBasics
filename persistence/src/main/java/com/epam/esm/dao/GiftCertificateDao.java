package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;

import java.util.List;


public interface GiftCertificateDao  {
    List<GiftCertificate> findByOrderByNameAsc();
    List<GiftCertificate> findByOrderByNameDesc();
    List<GiftCertificate> findByOrderByCreateDateAsc();
    List<GiftCertificate> findByOrderByCreateDateDesc();
    List<GiftCertificate> findByPartOfName(String name);
    List<GiftCertificate> findByPartOfDescription(String description);
    List<GiftCertificate> findByTags(List<Tag> tags);
    List<GiftCertificate> findAll();
    GiftCertificate save(GiftCertificate giftCertificate);
    void delete(GiftCertificate giftCertificate);
    void update(GiftCertificate oldCert,GiftCertificate newCert);
    GiftCertificate findById(Integer id);
}
