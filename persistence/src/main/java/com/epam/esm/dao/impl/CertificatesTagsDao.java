package com.epam.esm.dao.impl;

import com.epam.esm.dao.reader.CertificatesTagsMapper;
import com.epam.esm.domain.CertificatesTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificatesTagsDao {

    private JdbcTemplate jdbcTemplate;
    private CertificatesTagsMapper mapper;

    @Autowired
    public void setMapper(CertificatesTagsMapper mapper) {
        this.mapper = mapper;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String INSERT_CERTIFICATE_TAG = "INSERT INTO certificates_tags(cert_id,tag_id) " +
            "VALUES(?,?);";
    private static final String DELETE_CERTIFICATE_TAG = "DELETE FROM certificates_tags ";
    private static final String UPDATE_CERTIFICATE_TAG = "UPDATE certificates_tags SET " +
            " cert_id = ?, tag_id = ? ";
    private static final String SELECT_CERTIFICATE_TAG = "SELECT * FROM certificates_tags ";

    private static final String FIND_BY_CERTIFICATE_ID = SELECT_CERTIFICATE_TAG + " WHERE cert_id = ? ";
    private static final String FIND_BY_TAG_ID = SELECT_CERTIFICATE_TAG + " WHERE tag_id = ? ";


    public void save(CertificatesTags cert) {
        jdbcTemplate.update(INSERT_CERTIFICATE_TAG,cert.getCertificateId(),cert.getTagId());
    }
    public List<CertificatesTags> findAllByCertificateId(int id) {
        return  jdbcTemplate.query(FIND_BY_CERTIFICATE_ID,mapper,id);
    }
    public List<CertificatesTags> findAllByTagId(int id) {
        return  jdbcTemplate.query(FIND_BY_TAG_ID,mapper,id);
    }
}
