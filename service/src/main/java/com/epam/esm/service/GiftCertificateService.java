package com.epam.esm.service;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;

import java.util.List;
import java.util.Set;

public interface GiftCertificateService {
    void save(GiftCertificate giftCertificate);
    void update(GiftCertificate giftCertificate);
    void delete(int id);
    List<GiftCertificate> findByPartOfName(String name);
    List<GiftCertificate> findByPartOfDescription(String description);
    List<GiftCertificate> findAll();
    List<GiftCertificate> findAllByTag(String tag);
    GiftCertificate findById(int id);
    List<GiftCertificate> getAscendingDate();
    List<GiftCertificate> getAscendingName();
    List<GiftCertificate> getDescendingDate();
    List<GiftCertificate> getDescendingName();
    Set<Tag> findCertificateTags(int id);
    GiftCertificate findByName(String name);
    void parseCertificateTags(Set<Tag> tags, int id);
}
