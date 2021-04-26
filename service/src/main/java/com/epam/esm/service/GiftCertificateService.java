package com.epam.esm.service;

import com.epam.esm.domain.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    void delete(Integer id);
    GiftCertificate save(GiftCertificate giftCertificate);
    List<GiftCertificate> findAll(int page,int size);
    List<GiftCertificate> getSortedList(String sortType,String sortBy,int page,int size);
    GiftCertificate findById(Integer id);
    List<GiftCertificate> findByPartOfName(String name,int page,int size);
    List<GiftCertificate> findByPartOfDescription(String description,int page,int size);
    List<GiftCertificate> findAllByTags(List<String> tags,int page,int size);
    GiftCertificate update(GiftCertificate giftCertificate);
}
