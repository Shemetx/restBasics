package com.epam.esm.service;

import com.epam.esm.dao.impl.CertificatesTagsDaoImpl;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.specification.impl.FindAll;
import com.epam.esm.specification.impl.FindById;
import com.epam.esm.specification.impl.certificatesTags.FindByCertificateId;
import com.epam.esm.specification.impl.certificatesTags.FindByTagId;
import com.epam.esm.specification.impl.tags.FindByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificateTagsService {

    private CertificatesTagsDaoImpl dao;

    @Autowired
    public void setDao(CertificatesTagsDaoImpl dao) {
        this.dao = dao;
    }

    public List<CertificatesTags> findAllByCertificateId(int id) {
        return dao.queryAll(new FindByCertificateId(id));
    }

    public List<CertificatesTags> findAllByTagId(Tag tag) {
        List<CertificatesTags> certificatesTags = dao.queryAll(new FindByTagId(tag.getId()));
        if (certificatesTags.isEmpty()) {
            throw new EntityNotFoundException("Certificates with tag: '" + tag.getName() + "' not found");
        }

        return certificatesTags;}
    public void save(CertificatesTags certificatesTags) {
        dao.add(certificatesTags);
    }

}
