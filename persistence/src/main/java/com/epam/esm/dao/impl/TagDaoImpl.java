package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.domain.Tag;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_TAG = "select t from Tag t ";
    private static final String FIND_BY_NAME = SELECT_TAG + " where t.name = ?1 ";

    @Override
    public List<Tag> findAll() {
        return entityManager.createQuery(SELECT_TAG).getResultList();
    }

    @Override
    public Tag findByName(String name) {
        Object singleResult = entityManager.createQuery(FIND_BY_NAME)
                .setParameter(1, name)
                .getSingleResult();
        return (Tag)singleResult;
    }

    @Override
    public Tag findById(Integer id) {
        return entityManager.find(Tag.class,id);
    }


    @Override
    public void save(Tag tag) {
        entityManager.persist(tag);
    }


    @Override
    public void delete(Tag tag) {
        entityManager.remove(tag);
    }
}
