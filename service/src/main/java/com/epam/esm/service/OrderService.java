package com.epam.esm.service;

import com.epam.esm.domain.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {
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
     * @return the list
     */
    List<Order> findAll(int page, int size);

    /**
     * Find by user id orders.
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
