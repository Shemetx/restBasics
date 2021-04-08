package com.epam.esm.dao;

import com.epam.esm.dao.reader.CertificatesTagsMapper;
import com.epam.esm.domain.CertificatesTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * Certificates tags dao to work with many to many table
 */
@Component
public class CertificatesTagsDao {

    private JdbcTemplate jdbcTemplate;
    private CertificatesTagsMapper mapper;

    /**
     * Sets mapper.
     *
     * @param mapper the mapper
     */
    @Autowired
    public void setMapper(CertificatesTagsMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Sets jdbc template.
     *
     * @param jdbcTemplate the jdbc template
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_CERTIFICATE_TAG = "INSERT INTO certificates_tags(cert_id,tag_id) " +
            "VALUES(?,?);";
    private static final String SELECT_CERTIFICATE_TAG = "SELECT * FROM certificates_tags ";

    private static final String FIND_BY_BOTH_ID = SELECT_CERTIFICATE_TAG + " WHERE cert_id = ? AND tag_id = ? ";

    /**
     * Saves entity to database
     *
     * @param cert the cert
     */
    public void save(CertificatesTags cert) {
        jdbcTemplate.update(INSERT_CERTIFICATE_TAG, cert.getCertificateId(), cert.getTagId());
    }

    /**
     * Find by both id certificates tags.
     *
     * @param certId the cert id
     * @param tagId  the tag id
     * @return the certificates tags
     */
    public CertificatesTags findByBothId(int certId, int tagId) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_BOTH_ID, mapper, certId, tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
