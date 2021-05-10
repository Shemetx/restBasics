package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of GiftCertificateDao
 */
@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private static final String SELECT_GIFT_CERTIFICATE = "select g from GiftCertificate g";
    private static final String ORDER_BY_NAME_ASC = SELECT_GIFT_CERTIFICATE + " order by g.name asc";
    private static final String ORDER_BY_NAME_DESC = SELECT_GIFT_CERTIFICATE + " order by g.name desc";
    private static final String ORDER_BY_DATE_ASC = SELECT_GIFT_CERTIFICATE + " order by g.createDate asc";
    private static final String ORDER_BY_DATE_DESC = SELECT_GIFT_CERTIFICATE + " order by g.createDate desc";
    private static final String FIND_BY_PART_OF_NAME = SELECT_GIFT_CERTIFICATE + " where locate(?1,g.name) >= 1  ";
    private static final String FIND_BY_PART_OF_DESCRIPTION = SELECT_GIFT_CERTIFICATE + " where locate(?1,g.description) >= 1";
    private static final String FIND_BY_TAG_ID = "select * from gift_certificate gc\n" +
            "where ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GiftCertificate> findByOrderByNameAsc(int page, int size) {
        return entityManager.createQuery(ORDER_BY_NAME_ASC, GiftCertificate.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByOrderByNameDesc(int page, int size) {
        return entityManager.createQuery(ORDER_BY_NAME_DESC, GiftCertificate.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByOrderByCreateDateAsc(int page, int size) {
        return entityManager.createQuery(ORDER_BY_DATE_ASC, GiftCertificate.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByOrderByCreateDateDesc(int page, int size) {
        return entityManager.createQuery(ORDER_BY_DATE_DESC, GiftCertificate.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByPartOfName(String name, int page, int size) {
        return entityManager.createQuery(FIND_BY_PART_OF_NAME, GiftCertificate.class)
                .setParameter(1, name)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String description, int page, int size) {
        return entityManager.createQuery(FIND_BY_PART_OF_DESCRIPTION, GiftCertificate.class)
                .setParameter(1, description)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByTags(List<Tag> tags, int page, int size) {
        StringBuilder result = getDynamicFindByTags(tags);
        Query nativeQuery = entityManager.createNativeQuery(String.valueOf(result), GiftCertificate.class)
                .setFirstResult(page)
                .setMaxResults(size);
        for (int i = 0; i < tags.size(); i++) {
            nativeQuery.setParameter(i + 1, tags.get(i).getId());
        }
        return nativeQuery.getResultList();
    }

    private StringBuilder getDynamicFindByTags(List<Tag> tags) {
        StringBuilder result = new StringBuilder(FIND_BY_TAG_ID);
        for (int i = 0; i < tags.size(); i++) {
            result.append("exists(select * from certificates_tags c where cert_id = gc.id and tag_id = ?")
                    .append(i + 1).append(") ");
            if (i < tags.size() - 1) {
                result.append("and ");
            }
        }
        return result;
    }

    @Override
    public List<GiftCertificate> findAll(int page, int size) {
        return entityManager.createQuery(SELECT_GIFT_CERTIFICATE, GiftCertificate.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        return entityManager.merge(giftCertificate);
    }

    @Override
    public void delete(GiftCertificate giftCertificate) {
        entityManager.remove(giftCertificate);
    }

    @Override
    public GiftCertificate findById(Integer id) {
        return entityManager.find(GiftCertificate.class, id);
    }


    /**
     * Updates only fields passes with request
     */
    public void update(GiftCertificate newCert) {
        GiftCertificate merge = entityManager.find(GiftCertificate.class,newCert.getId());
        if (newCert.getName() != null) {
            merge.setName(newCert.getName());
        }
        if (newCert.getDescription() != null) {
            merge.setDescription(newCert.getDescription());
        }
        if (newCert.getPrice() != null) {
            merge.setPrice(newCert.getPrice());
        }
        if (newCert.getDuration() != null) {
            merge.setDuration(newCert.getDuration());
        }
        if (newCert.getTags() != null) {
            merge.getTags().addAll(newCert.getTags());
        }
    }
}
