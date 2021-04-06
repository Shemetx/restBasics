package com.epam.esm.dao.impl;

import com.epam.esm.dao.reader.TagMapper;
import com.epam.esm.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagsDao {

    private JdbcTemplate jdbcTemplate;
    private TagMapper tagMapper;

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String INSERT_TAG = "INSERT INTO tag(name) " +
            "VALUES(?);";
    private static final String DELETE_TAG = "DELETE FROM tag ";
    private static final String SELECT_TAG = "SELECT * FROM tag ";
    private static final String UPDATE_TAG = "UPDATE tag SET " +
            " name = ? ";
    private static final String FIND_BY_ID = SELECT_TAG + " WHERE id = ? ";
    private static final String FIND_BY_NAME  = SELECT_TAG + " WHERE name = ? ";

    public List<Tag> index() {
        return jdbcTemplate.query(SELECT_TAG,tagMapper);
    }
    public Tag findById(int id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,tagMapper,id);
    }
    public Tag findByName(String name ) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME,tagMapper,name);
    }
    public void save(Tag tag) {
        jdbcTemplate.update(INSERT_TAG,tag.getName());
    }
    public void update(Tag tag) {
        jdbcTemplate.update(UPDATE_TAG + " WHERE id = ?",tag.getName(),tag.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_TAG + " WHERE id = ?",id);
    }

}
