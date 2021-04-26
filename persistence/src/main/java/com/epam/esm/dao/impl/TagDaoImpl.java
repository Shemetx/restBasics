package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.domain.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class TagDaoImpl implements TagDao {

    private static final String SELECT_TAG = "select t from Tag t ";
    private static final String FIND_BY_NAME = SELECT_TAG + " where t.name = ?1 ";
    private static final String FIND_USER_MAX_COST = "select u.id from (select e.id,sum(w.cost) maxCost\n" +
            "    from user e\n" +
            "    join user_order w on e.id = w.user_id\n" +
            "    group by e.id\n" +
            ") u\n" +
            "having max(u.maxCost)";
    private static final String FIND_MOST_WIDELY_USED_TAG = "select *\n" +
            "from tag\n" +
            "where id = (select tag_id\n" +
            "            from certificates_tags gc\n" +
            "                     join order_items oi on gc.cert_id = oi.item_id\n" +
            "                     join user_order uo on oi.order_id = uo.id\n" +
            "            where uo.user_id = ?1\n" +
            "            group by tag_id\n" +
            "            order by count(*) desc\n" +
            "            limit 1);";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> findAll(int page, int size) {
        return entityManager.createQuery(SELECT_TAG, Tag.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Tag findByName(String name) {
        Object singleResult = entityManager.createQuery(FIND_BY_NAME)
                .setParameter(1, name)
                .getSingleResult();
        return (Tag) singleResult;
    }

    @Override
    public Tag findById(Integer id) {
        return entityManager.find(Tag.class, id);
    }


    @Override
    public void save(Tag tag) {
        entityManager.persist(tag);
    }


    @Override
    public void delete(Tag tag) {
        entityManager.remove(tag);
    }

    @Override
    public Tag findMostUsed() {
        Integer maxCostUser = findMaxCostUser();
        return (Tag) entityManager.createNativeQuery(FIND_MOST_WIDELY_USED_TAG, Tag.class).setParameter(1, maxCostUser).getSingleResult();

    }

    private Integer findMaxCostUser() {
        Object singleResult = entityManager.createNativeQuery(FIND_USER_MAX_COST).getSingleResult();
        return (Integer) singleResult;
    }

    private CriteriaQuery<Tag> getCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> from = criteriaQuery.from(Tag.class);
        return criteriaQuery.select(from);
    }
}
