package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    @PersistenceContext
    private EntityManager entityManager;
    private static final String SELECT_GIFT_CERTIFICATE = "select g from GiftCertificate g";
    private static final String ORDER_BY_NAME_ASC = SELECT_GIFT_CERTIFICATE + " order by g.name asc";
    private static final String ORDER_BY_NAME_DESC = SELECT_GIFT_CERTIFICATE + " order by g.name desc";
    private static final String ORDER_BY_DATE_ASC = SELECT_GIFT_CERTIFICATE + " order by g.createDate asc";
    private static final String ORDER_BY_DATE_DESC = SELECT_GIFT_CERTIFICATE + " order by g.createDate desc";
    private static final String FIND_BY_PART_OF_NAME = SELECT_GIFT_CERTIFICATE + " where locate(?1,g.name) >= 1  ";
    private static final String FIND_BY_PART_OF_DESCRIPTION = SELECT_GIFT_CERTIFICATE + " where locate(?1,g.description) >= 1";
    private static final String FIND_BY_TAG_ID = SELECT_GIFT_CERTIFICATE + " join g.tags tags on tags.id = ?1";

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
                .setParameter(1,name)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByPartOfDescription(String description) {
        return entityManager.createQuery(FIND_BY_PART_OF_DESCRIPTION)
                .setParameter(1,description)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findByTag(Integer id) {
        return entityManager.createQuery(FIND_BY_TAG_ID)
                .setParameter(1,id)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findAll() {
      return   entityManager.createQuery(SELECT_GIFT_CERTIFICATE).getResultList();
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
      return  entityManager.merge(giftCertificate);
    }

    @Override
    public void delete(GiftCertificate giftCertificate) {
        entityManager.remove(giftCertificate);
    }

    @Override
    public GiftCertificate findById(Integer id) {
        return entityManager.find(GiftCertificate.class,id);
    }
    /**
     * Updates only fields passes with request
     *
     *
     */
    public void update(GiftCertificate oldCert,GiftCertificate newCert) {
        GiftCertificate merge = entityManager.merge(oldCert);
        if (newCert.getName()!=null) {
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
