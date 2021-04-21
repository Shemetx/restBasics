package com.epam.esm.service;

import com.epam.esm.domain.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    void delete(Integer id);
    GiftCertificate save(GiftCertificate giftCertificate);
    List<GiftCertificate> findAll();
    List<GiftCertificate> getSortedList(String sortType,String sortBy);
    GiftCertificate findById(Integer id);
    List<GiftCertificate> findByPartOfName(String name);
    List<GiftCertificate> findByPartOfDescription(String description);
    List<GiftCertificate> findAllByTag(String tag);
    GiftCertificate update(GiftCertificate giftCertificate);
}
