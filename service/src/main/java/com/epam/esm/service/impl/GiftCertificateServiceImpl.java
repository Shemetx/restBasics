package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.util.SortingTypes;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.CertificatesTagsService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * implementation of GiftCertificateService to connect controller with dao layer
 */
@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private CertificatesTagsService certificateTagsServiceImpl;
    private TagService tagServiceImpl;
    private GiftCertificateDao giftCertificateDaoImpl;

    /**
     * Sets tag service.
     *
     * @param tagServiceImpl the tag service
     */
    @Autowired
    public void setTagService(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    /**
     * Sets certificate tags service.
     *
     * @param certificatesTagsServiceImpl the certificate tags service
     */
    @Autowired
    public void setCertificateTagsService(CertificatesTagsServiceImpl certificatesTagsServiceImpl) {
        this.certificateTagsServiceImpl = certificatesTagsServiceImpl;
    }

    /**
     * Sets gift certificate dao.
     *
     * @param giftCertificateDaoImpl the gift certificate dao
     */
    @Autowired
    public void setGiftCertificateDao(GiftCertificateDaoImpl giftCertificateDaoImpl) {
        this.giftCertificateDaoImpl = giftCertificateDaoImpl;
    }

    /**
     * Saves entity
     *
     * @param giftCertificate the gift certificate
     */
    public void save(GiftCertificate giftCertificate) {
        try {
            giftCertificateDaoImpl.save(giftCertificate);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Tag with name: '" + giftCertificate.getName() + "' already exists");
        }
    }

    /**
     * Update entity
     *
     * @param giftCertificate the gift certificate
     */
    public void update(GiftCertificate giftCertificate) {
        giftCertificateDaoImpl.update(giftCertificate);
    }

    /**
     * Find by part of name list.
     *
     * @param name the name
     * @return the list
     */
    public List<GiftCertificate> findByPartOfName(String name) {
        List<GiftCertificate> certificateList = giftCertificateDaoImpl.findByPartOfName(name);
        if (certificateList.isEmpty()) {
            throw new EntityNotFoundException("Certificates with name: " + name + " not found");
        }
        return certificateList;
    }

    /**
     * Find by part of description list.
     *
     * @param description the description
     * @return the list
     */
    public List<GiftCertificate> findByPartOfDescription(String description) {
        List<GiftCertificate> certificateList = giftCertificateDaoImpl.findByPartOfDescription(description);
        if (certificateList.isEmpty()) {
            throw new EntityNotFoundException("Certificates with description: " + description + " not found");
        }
        return certificateList;
    }

    /**
     * Delete entity
     *
     * @param id the id
     */
    public void delete(int id) {
        giftCertificateDaoImpl.delete(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<GiftCertificate> findAll() {
        return giftCertificateDaoImpl.index();
    }

    /**
     * Find all by tag list.
     *
     * @param tag the tag
     * @return the list
     */
    public List<GiftCertificate> findAllByTag(String tag) {
        Tag tagByName = tagServiceImpl.findByName(tag);
        List<GiftCertificate> byTagId = giftCertificateDaoImpl.findByTagId(tagByName.getId());
        if (byTagId.isEmpty()) {
            throw new EntityNotFoundException("Gift certificates with tag name: " + tag + " not found");
        }
        return byTagId;
    }

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    public GiftCertificate findById(int id) {
        GiftCertificate certificate = giftCertificateDaoImpl.findById(id);
        if (certificate == null) {
            throw new EntityNotFoundException("Gift certificate with id: " + id + " not found");
        }
        return certificate;
    }

    /**
     *  Gets sorted list
     *
     * @param sortType type of sort asc/desc
     * @param sortBy sorting parameter ex: name/date
     * @return sorted list
     */
    public List<GiftCertificate> getSortedList(String sortType, String sortBy) {
        List<GiftCertificate> sortedList = findAll();
        SortingTypes sortingTypes = SortingTypes.resolveByName(sortType,sortBy);
        switch (sortingTypes) {
            case ASC_NAME:
                sortedList.sort(Comparator.comparing(GiftCertificate::getName));
                break;
            case ASC_DATE:
                sortedList.sort(Comparator.comparing(GiftCertificate::getCreateDate));
                break;
            case DESC_NAME:
                sortedList.sort(Comparator.comparing(GiftCertificate::getName, Comparator.reverseOrder()));
                break;
            case DESC_DATE:
                sortedList.sort(Comparator.comparing(GiftCertificate::getCreateDate, Comparator.reverseOrder()));
                break;
            default:
        }
        return sortedList;
    }

    /**
     * Find all certificate tags
     *
     * @param id the id
     * @return the set
     */
    public Set<Tag> findCertificateTags(int id) {
        List<Tag> certificateTags = tagServiceImpl.findCertificateTags(id);
        return new HashSet<>(certificateTags);
    }

    /**
     * Find by name gift certificate.
     *
     * @param name the name
     * @return the gift certificate
     */
    public GiftCertificate findByName(String name) {
        GiftCertificate certificate = giftCertificateDaoImpl.findByName(name);
        if (certificate == null) {
            throw new EntityNotFoundException("Certificate with name: '" + name + "' not found");
        }
        return certificate;
    }

    /**
     * Gets tags from request
     * If tag doesnt exists, creates tag and add in certificate
     * If tag already exists, check if certificate has it and add if doesnt
     *
     * @param tags the tags
     * @param id   the id
     */

    @Transactional
    public void parseCertificateTags(Set<Tag> tags, int id) {
        List<Tag> currentTags = tagServiceImpl.findAll();
        if (tags != null) {
            for (Tag tag : tags) {
                Optional<Tag> byName = currentTags.stream().filter(x -> tag.getName().equals(x.getName())).findFirst();
                if (!byName.isPresent()) {
                    tagServiceImpl.save(tag);
                }
                CertificatesTags certificatesTags = CertificatesTags.Builder.newInstance()
                        .setCertificateId(id)
                        .setTagId(tagServiceImpl.findByName(tag.getName()).getId())
                        .build();
                CertificatesTags byIds = certificateTagsServiceImpl.findByIds(certificatesTags.getCertificateId(), certificatesTags.getTagId());
                if (byIds == null) {
                    certificateTagsServiceImpl.save(certificatesTags);
                }
            }
        }
    }
}
