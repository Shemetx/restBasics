package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificatesTagsDao;
import com.epam.esm.dao.impl.CertificatesTagsDaoImpl;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.service.CertificatesTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * implementation of Certificate tags service to connect other services with many to many table
 */
@Component
public class CertificatesTagsServiceImpl implements CertificatesTagsService {

    private CertificatesTagsDao dao;

    /**
     * Sets dao.
     *
     * @param dao the dao
     */
    @Autowired
    public void setDao(CertificatesTagsDaoImpl dao) {
        this.dao = dao;
    }

    /**
     * Saves certificate and tag in many to many table
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
