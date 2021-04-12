package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

/**
 * GiftCertificate service to connect controller with dao layer
 */
@Component
public class GiftCertificateService {

    private CertificateTagsService certificateTagsService;
    private TagService tagService;
    private GiftCertificateDao giftCertificateDao;
    private PlatformTransactionManager transactionManager;

    /**
     * Sets transaction manager.
     *
     * @param transactionManager the transaction manager
     */
    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * Sets tag service.
     *
     * @param tagService the tag service
     */
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Sets certificate tags service.
     *
     * @param certificateTagsService the certificate tags service
     */
    @Autowired
    public void setCertificateTagsService(CertificateTagsService certificateTagsService) {
        this.certificateTagsService = certificateTagsService;
    }

    /**
     * Sets gift certificate dao.
     *
     * @param giftCertificateDao the gift certificate dao
     */
    @Autowired
    public void setGiftCertificateDao(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    /**
     * Saves entity
     *
     * @param giftCertificate the gift certificate
     */
    public void save(GiftCertificate giftCertificate) {
        try {
        giftCertificateDao.save(giftCertificate);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Tag with name: '" +giftCertificate.getName() +"' already exists" );
        }
    }

    /**
     * Update entity
     *
     * @param giftCertificate the gift certificate
     */
    public void update(GiftCertificate giftCertificate) {
        giftCertificateDao.update(giftCertificate);
    }

    /**
     * Find by part of name list.
     *
     * @param name the name
     * @return the list
     */
    public List<GiftCertificate> findByPartOfName(String name) {
        List<GiftCertificate> certificateList = giftCertificateDao.findByPartOfName(name);
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
        List<GiftCertificate> certificateList = giftCertificateDao.findByPartOfDescription(description);
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
        giftCertificateDao.delete(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<GiftCertificate> findAll() {
        return giftCertificateDao.index();
    }

    /**
     * Find all by tag list.
     *
     * @param tag the tag
     * @return the list
     */
    public List<GiftCertificate> findAllByTag(String tag) {
        Tag tagByName = tagService.findByName(tag);
        List<GiftCertificate> byTagId = giftCertificateDao.findByTagId(tagByName.getId());
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
        GiftCertificate certificate = giftCertificateDao.findById(id);
        if (certificate == null) {
            throw new EntityNotFoundException("Gift certificate with id: " + id + " not found");
        }
        return certificate;
    }

    /**
     * Gets all by ascending date.
     *
     * @return the ascending date
     */
    public List<GiftCertificate> getAscendingDate() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getCreateDate));
        return all;
    }

    /**
     * Gets all by ascending name.
     *
     * @return the ascending name
     */
    public List<GiftCertificate> getAscendingName() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getName));
        return all;
    }

    /**
     * Gets all by descending date.
     *
     * @return the descending date
     */
    public List<GiftCertificate> getDescendingDate() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getCreateDate, Comparator.reverseOrder()));
        return all;
    }

    /**
     * Gets all by descending name.
     *
     * @return the descending name
     */
    public List<GiftCertificate> getDescendingName() {
        List<GiftCertificate> all = findAll();
        all.sort(Comparator.comparing(GiftCertificate::getName, Comparator.reverseOrder()));
        return all;
    }

    /**
     * Find all certificate tags
     *
     * @param id the id
     * @return the set
     */
    public Set<Tag> findCertificateTags(int id) {
        List<Tag> certificateTags = tagService.findCertificateTags(id);
        return new HashSet<>(certificateTags);
    }

    /**
     * Find by name gift certificate.
     *
     * @param name the name
     * @return the gift certificate
     */
    public GiftCertificate findByName(String name) {
        GiftCertificate certificate = giftCertificateDao.findByName(name);
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
    public void parseCertificateTags(Set<Tag> tags, int id) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                List<Tag> currentTags = tagService.findAll();
                if (tags != null) {
                    for (Tag tag : tags) {
                        Optional<Tag> byName = currentTags.stream().filter(x -> tag.getName().equals(x.getName())).findFirst();
                        if (!byName.isPresent()) {
                            tagService.save(tag);
                        }
                        CertificatesTags certificatesTags = CertificatesTags.Builder.newInstance()
                                .setCertificateId(id)
                                .setTagId(tagService.findByName(tag.getName()).getId())
                                .build();
                        CertificatesTags byIds = certificateTagsService.findByIds(certificatesTags.getCertificateId(), certificatesTags.getTagId());
                        if (byIds == null) {
                            certificateTagsService.save(certificatesTags);
                        }
                    }
                }
            }
        });

    }
}
