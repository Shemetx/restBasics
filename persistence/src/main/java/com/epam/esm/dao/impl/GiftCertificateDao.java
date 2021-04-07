package com.epam.esm.dao.impl;


import com.epam.esm.dao.reader.GiftCertificateMapper;
import com.epam.esm.dao.reader.TagMapper;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateDao {

    private JdbcTemplate jdbcTemplate;
    private GiftCertificateMapper mapper;

    @Autowired
    public void setMapper(GiftCertificateMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificate" +
            "(name,description,price,create_date,last_update_date,duration) " +
            "VALUES(?,?,?,?,?,?);";
    private static final String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificate ";
    private static final String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificate SET " +
            " name = ?, description = ?, price = ?, create_date = ?, last_update_date = ?, duration = ? ";
    private static final String SELECT_GIFT_CERTIFICATE = "SELECT * FROM gift_certificate ";
    private static final String FIND_BY_ID = SELECT_GIFT_CERTIFICATE + " WHERE id = ? ";
    private static final String FIND_BY_NAME = SELECT_GIFT_CERTIFICATE + " WHERE name = ? ";
    private static final String FIND_BY_PART_NAME = SELECT_GIFT_CERTIFICATE + " WHERE locate(?,name)";
    private static final String FIND_BY_PART_DESCRIPTION = SELECT_GIFT_CERTIFICATE + " WHERE locate(?,description)";
    private static final String FIND_BY_TAG_ID = SELECT_GIFT_CERTIFICATE + " JOIN certificates_tags ct on gift_certificate.id = ct.cert_id\n" +
            "WHERE tag_id = ?; ";


    public List<GiftCertificate> index() {
        return
                jdbcTemplate.query(SELECT_GIFT_CERTIFICATE, mapper);
    }

    public GiftCertificate findById(int id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, mapper, id);
    }

    public GiftCertificate findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME, mapper, name);
    }

    public List<GiftCertificate> findByTagId(int id) {
        return jdbcTemplate.query(FIND_BY_TAG_ID, mapper, id);
    }

    public List<GiftCertificate> findByPartOfName(String name) {
        return jdbcTemplate.query(FIND_BY_PART_NAME, mapper, name);
    }

    public List<GiftCertificate> findByPartOfDescription(String description) {
        return jdbcTemplate.query(FIND_BY_PART_DESCRIPTION, mapper, description);
    }

    public void save(GiftCertificate cert) {
        jdbcTemplate.update(INSERT_GIFT_CERTIFICATE, cert.getName(), cert.getDescription()
                , cert.getPrice(), cert.getCreateDate(), cert.getLastUpdateDate(), cert.getDuration());
    }

    public void update(GiftCertificate cert) {
        jdbcTemplate.update(UPDATE_GIFT_CERTIFICATE + " WHERE id = ?", cert.getName(), cert.getDescription()
                , cert.getPrice(), cert.getCreateDate(), cert.getLastUpdateDate(), cert.getDuration(), cert.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_GIFT_CERTIFICATE + " WHERE id = ?", id);
    }

}
