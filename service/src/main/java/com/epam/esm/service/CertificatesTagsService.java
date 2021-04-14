package com.epam.esm.service;

import com.epam.esm.domain.CertificatesTags;

/**
 * The interface Certificates tags service.
 */
public interface CertificatesTagsService {
    /**
     * Save certificate and tag
     *
     * @param certificatesTags the certificates tags
     */
    void save(CertificatesTags certificatesTags);

    /**
     * Find by ids certificates tags.
     *
     * @param certId the cert id
     * @param tagId  the tag id
     * @return the certificates tags
     */
    CertificatesTags findByIds(int certId, int tagId);
}
