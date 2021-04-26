package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.domain.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    private static final String SELECT_ORDER = "select t from Order t ";
    private static final String FIND_BY_USER_ID = SELECT_ORDER + "where t.customer.id = ?1";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order save(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public List<Order> findAll(int page,int size) {
        return entityManager.createQuery(SELECT_ORDER,Order.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Order> findByUserId(int id,int page,int size) {
        return entityManager.createQuery(FIND_BY_USER_ID,Order.class).setParameter(1, id)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }


}
