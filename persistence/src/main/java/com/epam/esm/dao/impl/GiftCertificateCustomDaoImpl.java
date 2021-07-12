package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateCustomDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of GiftCertificateDao
 */
@Component
public class GiftCertificateCustomDaoImpl implements GiftCertificateCustomDao {

    private static final String FIND_BY_TAG_ID = "select * from gift_certificate gc\n" +
            "where ";
    private static final String CERTIFICATE_EXISTS_BY_TAG = "exists(select * from certificates_tags c where cert_id = gc.id and tag_id = ?";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<GiftCertificate> findBySeveralTags(List<Tag> tags, Pageable pageable) {
        StringBuilder result = getDynamicFindByTags(tags);
        Query nativeQuery = entityManager.createNativeQuery(String.valueOf(result), GiftCertificate.class)
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize());
        for (int i = 0; i < tags.size(); i++) {
            nativeQuery.setParameter(i + 1, tags.get(i).getId());
        }
        List<GiftCertificate> resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList);
    }

    private StringBuilder getDynamicFindByTags(List<Tag> tags) {
        StringBuilder result = new StringBuilder(FIND_BY_TAG_ID);
        for (int i = 0; i < tags.size(); i++) {
            result.append(CERTIFICATE_EXISTS_BY_TAG)
                    .append(i + 1).append(") ");
            if (i < tags.size() - 1) {
                result.append("and ");
            }
        }
        return result;
    }

}
