package com.epam.esm.dao;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * The interface Gift certificate dao.
 */
public interface GiftCertificateCustomDao {

    /**
     * Find by several tags page.
     *
     * @param tags     the tags
     * @param pageable the pageable
     * @return the page
     */
    Page<GiftCertificate> findBySeveralTags(List<Tag> tags, Pageable pageable);
}
