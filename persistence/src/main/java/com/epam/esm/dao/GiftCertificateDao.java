package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;

import java.util.List;


public interface GiftCertificateDao  {
    List<GiftCertificate> findByOrderByNameAsc(int page,int size);
    List<GiftCertificate> findByOrderByNameDesc(int page,int size);
    List<GiftCertificate> findByOrderByCreateDateAsc(int page,int size);
    List<GiftCertificate> findByOrderByCreateDateDesc(int page,int size);
    List<GiftCertificate> findByPartOfName(String name,int page,int size);
    List<GiftCertificate> findByPartOfDescription(String description,int page,int size);
    List<GiftCertificate> findByTags(List<Tag> tags,int page,int size);
    List<GiftCertificate> findAll(int page,int size);
    GiftCertificate save(GiftCertificate giftCertificate);
    void delete(GiftCertificate giftCertificate);
    void update(GiftCertificate oldCert,GiftCertificate newCert);
    GiftCertificate findById(Integer id);
}
