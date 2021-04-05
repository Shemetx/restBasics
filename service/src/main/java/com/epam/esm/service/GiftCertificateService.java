package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.specification.impl.FindAll;
import com.epam.esm.specification.impl.FindById;
import com.epam.esm.specification.impl.certificatesTags.FindByTagId;
import com.epam.esm.specification.impl.giftCertificate.FindByPartOfDescription;
import com.epam.esm.specification.impl.giftCertificate.FindByPartOfName;
import com.epam.esm.specification.impl.giftCertificate.UpdateGiftCertificateById;;
import com.epam.esm.specification.impl.tags.FindByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GiftCertificateService {

    private CertificateTagsService certificateTagsService;
    private TagService tagService;
    private GiftCertificateDaoImpl giftCertificateDao;

    @Autowired
    public void setTagsDao(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setCertificateTagsService(CertificateTagsService certificateTagsService) {
        this.certificateTagsService = certificateTagsService;
    }

    @Autowired
    public void setGiftCertificateDao(GiftCertificateDaoImpl giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    public void save(GiftCertificate giftCertificate) {
        giftCertificateDao.add(giftCertificate);
    }

    public void update(GiftCertificate giftCertificate) {
        int update = giftCertificateDao.update(new UpdateGiftCertificateById(giftCertificate));
    }

    public List<GiftCertificate> findByPartOfName(String name) {
        List<GiftCertificate> certificateList = giftCertificateDao.queryAll(new FindByPartOfName(name));
        if (certificateList.isEmpty()) {
            throw new EntityNotFoundException("Certificates with name: " + name + " not found");
        }
        return certificateList;
    }

    public List<GiftCertificate> findByPartOfDescription(String description) {
        List<GiftCertificate> certificateList = giftCertificateDao.queryAll(new FindByPartOfDescription(description));
        if (certificateList.isEmpty()) {
            throw new EntityNotFoundException("Certificates with description: " + description + " not found");
        }
        return certificateList;
    }

    public void delete(GiftCertificate giftCertificate) {
        giftCertificateDao.delete(giftCertificate, new FindById(giftCertificate.getId()));
    }

    public List<GiftCertificate> findAll() {
        return new ArrayList<>(giftCertificateDao.queryAll(new FindAll()));
    }

    public List<GiftCertificate> findAllByTag(String tag) {
        Tag tagByName = tagService.findByName(tag);
        List<CertificatesTags> allByTagId = certificateTagsService.findAllByTagId(tagByName);
        List<GiftCertificate> certificateList = new ArrayList<>();

        for (CertificatesTags tags : allByTagId) {
            Optional<GiftCertificate> query = giftCertificateDao.query(new FindById(tags.getCertificateId()));
            query.ifPresent(certificateList::add);
        }
        return certificateList;
    }

    public GiftCertificate findById(int id) {
        Optional<GiftCertificate> certificate = giftCertificateDao.query(new FindById(id));
        if (!certificate.isPresent()) {
            throw new EntityNotFoundException("Gift certificate with id: " + id + " not found");
        }
        return certificate.get();
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

    public Set<Tag> findCertificateTags(GiftCertificate giftCertificate) {
        List<CertificatesTags> certificatesTags = certificateTagsService.findAllByCertificateId(giftCertificate.getId());
        Set<Tag> tagSet = new HashSet<>();
        for (CertificatesTags tags : certificatesTags) {
            Tag query = tagService.findById(tags.getTagId());
            tagSet.add(query);
        }
        return tagSet;
    }

    public GiftCertificate findByName(String name) {
        Optional<GiftCertificate> query = giftCertificateDao.query(new FindByName(name));
        if (!query.isPresent()) {
            throw new EntityNotFoundException("Certificate with name: '" + name + "' not found");
        }
        return query.get();
    }
}
