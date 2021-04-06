package com.epam.esm.service;

import com.epam.esm.dao.impl.CertificatesTagsDao;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificateTagsService {

    private CertificatesTagsDao dao;

    @Autowired
    public void setDao(CertificatesTagsDao dao) {
        this.dao = dao;
    }

    public List<CertificatesTags> findAllByCertificateId(int id) {
        return dao.findAllByCertificateId(id);
    }

    public List<CertificatesTags> findAllByTagId(int id) {
        List<CertificatesTags> certificatesTags = dao.findAllByTagId(id);
        if (certificatesTags.isEmpty()) {
            throw new EntityNotFoundException("Certificates with tag id: '" + id + "' not found");
        }

        return certificatesTags;
    }


    public void save(CertificatesTags certificatesTags) {
        dao.save(certificatesTags);
    }

}
