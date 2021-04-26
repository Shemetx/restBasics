package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.util.PageUtil;
import com.epam.esm.util.SortingTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private GiftCertificateDao giftCertificateDao;
    private TagService tagService;
    private PageUtil pageUtil;

    @Autowired
    public void setPageUtil(PageUtil pageUtil) {
        this.pageUtil = pageUtil;
    }

    @Autowired
    public void setGiftCertificateDao(GiftCertificateDaoImpl giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

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
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        GiftCertificate save;
        try {
            save = giftCertificateDao.save(giftCertificate);
        } catch (
                DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Gift certificate with name: '" + giftCertificate.getName() + "' already exists");
        }
        return save;
    }


    @Override
    public List<GiftCertificate> findAll(int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        return giftCertificateDao.findAll(page, size);
    }

    @Override
    public List<GiftCertificate> getSortedList(String sortType, String sortBy, int page, int size) {
        List<GiftCertificate> sortedList = Collections.emptyList();
        SortingTypes sortingTypes = SortingTypes.resolveByName(sortType, sortBy);
        page = pageUtil.getCorrectPage(page, size);
        switch (sortingTypes) {
            case ASC_NAME:
                sortedList = giftCertificateDao.findByOrderByNameAsc(page, size);
                break;
            case ASC_DATE:
                sortedList = giftCertificateDao.findByOrderByCreateDateAsc(page, size);
                break;
            case DESC_NAME:
                sortedList = giftCertificateDao.findByOrderByNameDesc(page, size);
                break;
            case DESC_DATE:
                sortedList = giftCertificateDao.findByOrderByCreateDateDesc(page, size);
                break;
            default:
        }
        return sortedList;
    }

    @Override
    public GiftCertificate findById(Integer id) {
        GiftCertificate certificate = giftCertificateDao.findById(id);
        if (certificate == null) {
            throw new EntityNotFoundException("Gift certificate with id: " + id + " not found");
        }
        return certificate;
    }

    @Override
    public List<GiftCertificate> findByPartOfName(String name, int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        List<GiftCertificate> byPartOfName = giftCertificateDao.findByPartOfName(name, page, size);
        if (byPartOfName.isEmpty()) {
            throw new EntityNotFoundException("Gift certificate with part of name: '" + name + "' not found");
        }
        return byPartOfName;
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String description, int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        List<GiftCertificate> byPartOfDescription = giftCertificateDao.findByPartOfDescription(description, page, size);
        if (byPartOfDescription.isEmpty()) {
            throw new EntityNotFoundException("Gift certificate with part of description: '" + description + "' not found");
        }
        return byPartOfDescription;
    }

    @Override
    public List<GiftCertificate> findAllByTags(List<String> tags, int page, int size) {
        page = pageUtil.getCorrectPage(page, size);
        List<Tag> collect = tags.stream().map(temp -> tagService.findByName(temp)).collect(Collectors.toList());
        List<GiftCertificate> byTag = giftCertificateDao.findByTags(collect, page, size);
        if (byTag.isEmpty()) {
            throw new EntityNotFoundException("Gift certificates with tag name: " + tags + " not found");
        }
        return byTag;
    }

    @Transactional
    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        giftCertificateDao.update(findById(giftCertificate.getId()), giftCertificate);
        return null;
    }


}
