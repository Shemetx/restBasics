package com.epam.esm.service;

import com.epam.esm.dao.CertificatesTagsDao;
import com.epam.esm.domain.CertificatesTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Certificate tags service to connect other services with many to many table
 */
@Component
public class CertificateTagsService {

    private CertificatesTagsDao dao;

    /**
     * Sets dao.
     *
     * @param dao the dao
     */
    @Autowired
    public void setDao(CertificatesTagsDao dao) {
        this.dao = dao;
    }

    /**
     * Saves certificate and tag to table
     *
     * @param certificatesTags the certificates tags
     */
    public void save(CertificatesTags certificatesTags) {
        dao.save(certificatesTags);
    }

    /**
     * Find by ids certificates tags.
     *
     * @param certId the cert id
     * @param tagId  the tag id
     * @return the certificates tags
     */
    public CertificatesTags findByIds(int certId, int tagId) {
        return dao.findByBothId(certId, tagId);
    }


}
