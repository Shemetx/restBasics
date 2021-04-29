package com.epam.esm.dao;

import com.epam.esm.domain.Order;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao {
    /**
     * Save order.
     *
     * @param order the order
     * @return the order
     */
    Order save(Order order);

    /**
     * Find all orders.
     *
     * @param page the page
     * @param size the size
     * @return the list of all orders
     */
    List<Order> findAll(int page, int size);

    /**
     * Find by user id list.
     *
     * @param id   the id
     * @param page the page
     * @param size the size
     * @return the list
     */
    List<Order> findByUserId(int id, int page, int size);

    /**
     * Find by id order.
     *
     * @param id the id
     * @return the order
     */
    Order findById(int id);

}
