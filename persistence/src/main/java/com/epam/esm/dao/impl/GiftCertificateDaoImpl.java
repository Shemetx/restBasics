package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private static final String SELECT_GIFT_CERTIFICATE = "select g from GiftCertificate g";
    private static final String ORDER_BY_NAME_ASC = SELECT_GIFT_CERTIFICATE + " order by g.name asc";
    private static final String ORDER_BY_NAME_DESC = SELECT_GIFT_CERTIFICATE + " order by g.name desc";
    private static final String ORDER_BY_DATE_ASC = SELECT_GIFT_CERTIFICATE + " order by g.createDate asc";
    private static final String ORDER_BY_DATE_DESC = SELECT_GIFT_CERTIFICATE + " order by g.createDate desc";
    private static final String FIND_BY_PART_OF_NAME = SELECT_GIFT_CERTIFICATE + " where locate(?1,g.name) >= 1  ";
    private static final String FIND_BY_PART_OF_DESCRIPTION = SELECT_GIFT_CERTIFICATE + " where locate(?1,g.description) >= 1";
    private static final String FIND_BY_TAG_ID = "select * from gift_certificate\n" +
            "join certificates_tags ct on gift_certificate.id = ct.cert_id\n" +
            "where ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GiftCertificate> findByOrderByNameAsc() {
        return entityManager.createQuery(ORDER_BY_NAME_ASC).getResultList();
    }

    @Override
    public List<GiftCertificate> findByOrderByNameDesc() {
        return entityManager.createQuery(ORDER_BY_NAME_DESC).getResultList();
    }

    @Override
    public List<GiftCertificate> findByOrderByCreateDateAsc() {
        return entityManager.createQuery(ORDER_BY_DATE_ASC).getResultList();
    }

    @Override
    public List<GiftCertificate> findByOrderByCreateDateDesc() {
        return entityManager.createQuery(ORDER_BY_DATE_DESC).getResultList();
    }

    @Override
    public List<GiftCertificate> findByPartOfName(String name) {
        return entityManager.createQuery(FIND_BY_PART_OF_NAME)
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String description) {
        return entityManager.createQuery(FIND_BY_PART_OF_DESCRIPTION)
                .setParameter(1, description)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByTags(List<Tag> tags) {
        StringBuilder result = getDynamicFindByTags(tags);
        Query nativeQuery = entityManager.createNativeQuery(String.valueOf(result), GiftCertificate.class);
        for(int i = 0; i <tags.size();i++) {
            nativeQuery.setParameter(i+1,tags.get(i).getId());
        }
        return nativeQuery.getResultList();
    }

    private StringBuilder getDynamicFindByTags(List<Tag> tags) {
        StringBuilder result = new StringBuilder(FIND_BY_TAG_ID);
        for (int i = 0; i < tags.size(); i++) {
            result.append("?").append(i+1).append(" ");
            if (i < tags.size() - 1) {
                result.append("and ");
            }
        }
        result.append("in (ct.tag_id)");
        return result;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return entityManager.createQuery(SELECT_GIFT_CERTIFICATE).getResultList();
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
    public void update(GiftCertificate oldCert, GiftCertificate newCert) {
        GiftCertificate merge = entityManager.merge(oldCert);
        if (newCert.getName() != null) {
            merge.setName(newCert.getName());
        }
        if (newCert.getDescription() != null) {
            merge.setDescription(newCert.getDescription());
        }
        if (newCert.getPrice() != 0) {
            merge.setPrice(newCert.getPrice());
        }
        if (newCert.getDuration() != 0) {
            merge.setDuration(newCert.getDuration());
        }
        if (!newCert.getTags().isEmpty()) {
            merge.getTags().addAll(newCert.getTags());
        }
    }
}
