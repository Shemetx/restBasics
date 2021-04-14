package com.epam.esm.dao.impl;


import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.reader.GiftCertificateMapper;
import com.epam.esm.domain.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * implementation of Gift certificate dao to work with gift_certificate table
 */
@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private JdbcTemplate jdbcTemplate;
    private GiftCertificateMapper mapper;

    /**
     * Sets mapper.
     *
     * @param mapper the mapper
     */
    @Autowired
    public void setMapper(GiftCertificateMapper mapper) {
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

    private static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificate" +
            "(name,description,price,create_date,last_update_date,duration) " +
            "VALUES(?,?,?,?,?,?);";
    private static final String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificate ";
    private static final String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificate SET ";
    private static final String SELECT_GIFT_CERTIFICATE = "SELECT * FROM gift_certificate ";
    private static final String FIND_BY_ID = SELECT_GIFT_CERTIFICATE + " WHERE id = ? ";
    private static final String FIND_BY_NAME = SELECT_GIFT_CERTIFICATE + " WHERE name = ? ";
    private static final String FIND_BY_PART_NAME = SELECT_GIFT_CERTIFICATE + " WHERE locate(?,name)";
    private static final String FIND_BY_PART_DESCRIPTION = SELECT_GIFT_CERTIFICATE + " WHERE locate(?,description)";
    private static final String FIND_BY_TAG_ID = SELECT_GIFT_CERTIFICATE + " JOIN certificates_tags ct on gift_certificate.id = ct.cert_id\n" +
            "WHERE tag_id = ?; ";


    /**
     * Returns all certificates from database
     *
     * @return the list
     */
    public List<GiftCertificate> index() {
        return
                jdbcTemplate.query(SELECT_GIFT_CERTIFICATE, mapper);
    }

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    public GiftCertificate findById(int id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Find by name gift certificate.
     *
     * @param name the name
     * @return the gift certificate
     */
    public GiftCertificate findByName(String name) {

        try {
            return jdbcTemplate.queryForObject(FIND_BY_NAME, mapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Find by tag id list.
     *
     * @param id the id
     * @return the list
     */
    public List<GiftCertificate> findByTagId(int id) {
        return jdbcTemplate.query(FIND_BY_TAG_ID, mapper, id);
    }

    /**
     * Find by part of name list.
     *
     * @param name the name
     * @return the list
     */
    public List<GiftCertificate> findByPartOfName(String name) {
        return jdbcTemplate.query(FIND_BY_PART_NAME, mapper, name);
    }

    /**
     * Find by part of description list.
     *
     * @param description the description
     * @return the list
     */
    public List<GiftCertificate> findByPartOfDescription(String description) {
        return jdbcTemplate.query(FIND_BY_PART_DESCRIPTION, mapper, description);
    }

    /**
     * Saves entity to database
     *
     * @param cert the cert
     */
    public void save(GiftCertificate cert) {
        cert.setCreateDate(LocalDateTime.now());
        cert.setLastUpdateDate(LocalDateTime.now());
        jdbcTemplate.update(INSERT_GIFT_CERTIFICATE, cert.getName(), cert.getDescription()
                , cert.getPrice(), cert.getCreateDate(), cert.getLastUpdateDate(), cert.getDuration());
    }

    /**
     * Updates only fields passes with request
     *
     * @param cert the cert
     */
    public void update(GiftCertificate cert) {
        MapSqlParameterSource mapParam = new MapSqlParameterSource();
        String updateQuery = UPDATE_GIFT_CERTIFICATE;
        if (cert.getName() != null) {
            updateQuery = updateQuery + " name = :name,";
            mapParam.addValue("name", cert.getName());
        }
        if (cert.getDescription() != null) {
            updateQuery = updateQuery + " description = :description,";
            mapParam.addValue("description", cert.getDescription());
        }
        if (cert.getPrice() != 0) {
            updateQuery = updateQuery + " price = :price, ";
            mapParam.addValue("price", cert.getPrice());
        }
        if (cert.getDuration() != 0) {
            updateQuery = updateQuery + " duration = :duration, ";
            mapParam.addValue("duration", cert.getDuration());
        }
        if (!mapParam.getValues().isEmpty()) {
            updateQuery = updateQuery + " last_update_date = :updateDate WHERE id = :id ";
            mapParam.addValue("updateDate", LocalDateTime.now());
            mapParam.addValue("id", cert.getId());
            NamedParameterJdbcTemplate namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
            namedJdbc.update(updateQuery, mapParam);
        }
    }

    /**
     * Delete from database by id
     *
     * @param id the id
     */
    public void delete(int id) {
        jdbcTemplate.update(DELETE_GIFT_CERTIFICATE + " WHERE id = ?", id);
    }

}
