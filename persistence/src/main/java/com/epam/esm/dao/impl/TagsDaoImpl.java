package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagsDao;
import com.epam.esm.dao.reader.TagMapper;
import com.epam.esm.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * implementation of Tags dao to work with tag table
 */
@Component
public class TagsDaoImpl implements TagsDao {

    private JdbcTemplate jdbcTemplate;
    private TagMapper tagMapper;

    /**
     * Sets tag mapper.
     *
     * @param tagMapper the tag mapper
     */
    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
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

    private static final String INSERT_TAG = "INSERT INTO tag(name) " +
            "VALUES(?);";
    private static final String DELETE_TAG = "DELETE FROM tag ";
    private static final String SELECT_TAG = "SELECT * FROM tag ";
    private static final String FIND_BY_ID = SELECT_TAG + " WHERE id = ? ";
    private static final String FIND_BY_NAME = SELECT_TAG + " WHERE name = ? ";
    private static final String FIND_CERTIFICATE_TAGS = SELECT_TAG + " JOIN certificates_tags ct on tag.id = ct.tag_id\n" +
            "WHERE cert_id = ?; ";

    /**
     * Returns all tags form database
     *
     * @return the list
     */
    public List<Tag> index() {
        return jdbcTemplate.query(SELECT_TAG, tagMapper);
    }

    /**
     * Find by id tag.
     *
     * @param id the id
     * @return the tag
     */
    public Tag findById(int id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, tagMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Find by name tag.
     *
     * @param name the name
     * @return the tag
     */
    public Tag findByName(String name) {
        try {
        return jdbcTemplate.queryForObject(FIND_BY_NAME, tagMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Find certificate tags list.
     *
     * @param id the id
     * @return the list
     */
    public List<Tag> findCertificateTags(int id) {
        return jdbcTemplate.query(FIND_CERTIFICATE_TAGS, tagMapper, id);
    }

    /**
     * Save in database
     *
     * @param tag the tag
     */
    public void save(Tag tag) {
            jdbcTemplate.update(INSERT_TAG, tag.getName());
    }

    /**
     * Delete from database by id
     *
     * @param id the id
     */
    public void delete(int id) {
        jdbcTemplate.update(DELETE_TAG + " WHERE id = ?", id);
    }

}
