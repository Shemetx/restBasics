package com.epam.esm.dao.impl;

import com.epam.esm.dao.reader.CertificatesTagsMapper;
import com.epam.esm.domain.CertificatesTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private static final String SELECT_CERTIFICATE_TAG = "SELECT * FROM certificates_tags ";

    private static final String FIND_BY_BOTH_ID = SELECT_CERTIFICATE_TAG + " WHERE cert_id = ? AND tag_id = ? ";

    public void save(CertificatesTags cert) {
        jdbcTemplate.update(INSERT_CERTIFICATE_TAG, cert.getCertificateId(), cert.getTagId());
    }

    public CertificatesTags findByBothId(int certId, int tagId) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_BOTH_ID, mapper, certId, tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
