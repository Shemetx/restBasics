package com.epam.esm.dao;

import com.epam.esm.domain.CertificatesTags;

/**
 * The interface Certificates tags dao.
 */
public interface CertificatesTagsDao {
    /**
     * Save entity.
     *
     * @param cert the cert
     */
    void save(CertificatesTags cert);

    /**
     * Find by both id certificates tags.
     *
     * @param certId the cert id
     * @param tagId  the tag id
     * @return the certificates tags
     */
    CertificatesTags findByBothId(int certId, int tagId);
}
