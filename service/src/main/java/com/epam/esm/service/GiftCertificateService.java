package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDao;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GiftCertificateService {

    private CertificateTagsService certificateTagsService;
    private TagService tagService;
    private GiftCertificateDao giftCertificateDao;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setCertificateTagsService(CertificateTagsService certificateTagsService) {
        this.certificateTagsService = certificateTagsService;
    }

    @Autowired
    public void setGiftCertificateDao(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    public void save(GiftCertificate giftCertificate) {
        giftCertificateDao.save(giftCertificate);
    }

    public void update(GiftCertificate giftCertificate) {
        giftCertificateDao.update(giftCertificate);
    }

    public List<GiftCertificate> findByPartOfName(String name) {
        List<GiftCertificate> certificateList = giftCertificateDao.findByPartOfName(name);
        if (certificateList.isEmpty()) {
            throw new EntityNotFoundException("Certificates with name: " + name + " not found");
        }
        return certificateList;
    }

    public List<GiftCertificate> findByPartOfDescription(String description) {
        List<GiftCertificate> certificateList = giftCertificateDao.findByPartOfDescription(description);
        if (certificateList.isEmpty()) {
            throw new EntityNotFoundException("Certificates with description: " + description + " not found");
        }
        return certificateList;
    }

    public void delete(int id) {
        giftCertificateDao.delete(id);
    }

    public List<GiftCertificate> findAll() {
        return giftCertificateDao.index();
    }

    public List<GiftCertificate> findAllByTag(String tag) {
        Tag tagByName = tagService.findByName(tag);
        List<GiftCertificate> byTagId = giftCertificateDao.findByTagId(tagByName.getId());
        if(byTagId.isEmpty()) {
            throw new EntityNotFoundException("Gift certificates with tag name: " + tag + " not found");
        }
        return byTagId;
    }

    public GiftCertificate findById(int id) {
        GiftCertificate certificate = giftCertificateDao.findById(id);
        if (certificate == null) {
            throw new EntityNotFoundException("Gift certificate with id: " + id + " not found");
        }
        return certificate;
    }

    public List<GiftCertificate> getAscendingDate() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getCreateDate));
        return all;
    }

    public List<GiftCertificate> getAscendingName() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getName));
        return all;
    }

    public List<GiftCertificate> getDescendingDate() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getCreateDate, Comparator.reverseOrder()));
        return all;
    }

    public List<GiftCertificate> getDescendingName() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getName, Comparator.reverseOrder()));
        return all;
    }

    public Set<Tag> findCertificateTags(int id) {
        List<Tag> certificateTags = tagService.findCertificateTags(id);
        return new HashSet<>(certificateTags);
    }

    public GiftCertificate findByName(String name) {
        GiftCertificate certificate = giftCertificateDao.findByName(name);
        if (certificate == null) {
            throw new EntityNotFoundException("Certificate with name: '" + name + "' not found");
        }
        return certificate;
    }
}
