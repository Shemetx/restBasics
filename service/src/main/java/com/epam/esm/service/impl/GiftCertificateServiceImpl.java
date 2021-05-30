package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.util.SortingTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of GiftCertificateService
 */
@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private TagService tagService;
    private GiftCertificateDao giftCertificateDao;

    @Autowired
    public void setGiftDao(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    /**
     * Sets tag service.
     *
     * @param tagService the tag service
     */
    @Autowired
    public void setTagService(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        GiftCertificate byId = findById(id);
        giftCertificateDao.delete(byId);
    }

    @Transactional
    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        return giftCertificateDao.save(giftCertificate);
    }


    @Override
    public Page<GiftCertificate> findAll(Specification<GiftCertificate> specification, int page, int size) {
        return giftCertificateDao.findAll(specification, PageRequest.of(page, size));
    }

    @Override
    public Page<GiftCertificate> getSortedList(Specification<GiftCertificate> specification,
                                               String sortType, String sortBy, int page, int size) {
        Page<GiftCertificate> sortedList = null;
        SortingTypes sortingTypes = SortingTypes.resolveByName(sortType, sortBy);
        switch (sortingTypes) {
            case ASC_NAME:
                sortedList = giftCertificateDao.findAll(specification, PageRequest.of(page, size, Sort.by("name").ascending()));
                break;
            case ASC_DATE:
                sortedList = giftCertificateDao.findAll(specification, PageRequest.of(page, size, Sort.by("createDate").ascending()));
                break;
            case DESC_NAME:
                sortedList = giftCertificateDao.findAll(specification, PageRequest.of(page, size, Sort.by("name").descending()));
                break;
            case DESC_DATE:
                sortedList = giftCertificateDao.findAll(specification, PageRequest.of(page, size, Sort.by("createDate").descending()));
                break;
            default:
        }
        return sortedList;
    }

    @Override
    public GiftCertificate findById(Integer id) {
        Optional<GiftCertificate> byId = giftCertificateDao.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundException("Gift certificate with id: " + id + " not found");
        }
        return byId.get();
    }


    @Override
    public Page<GiftCertificate> findAllByTags(List<String> tags, int page, int size) {
        List<Tag> collect = tags.stream().map(temp -> tagService.findByName(temp)).collect(Collectors.toList());
        Page<GiftCertificate> byTags = giftCertificateDao.findBySeveralTags(collect, PageRequest.of(page, size));
        if (byTags.isEmpty()) {
            throw new EntityNotFoundException("Gift certificates with tag name: " + tags + " not found");
        }
        return byTags;
    }

    @Transactional
    @Override
    public void update(GiftCertificate updated) {
        GiftCertificate byId = findById(updated.getId());
        if (updated.getName() != null) {
            byId.setName(updated.getName());
        }
        if (updated.getDescription() != null) {
            byId.setDescription(updated.getDescription());
        }
        if (updated.getPrice() != null) {
            byId.setPrice(updated.getPrice());
        }
        if (updated.getDuration() != null) {
            byId.setDuration(updated.getDuration());
        }
        if (updated.getTags() != null) {
            byId.getTags().addAll(updated.getTags());
        }
    }


}
