package com.epam.esm.dao;

import com.epam.esm.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface Order dao.
 */
public interface OrderDao extends JpaRepository<Order, Integer> {

    /**
     * Find by customer id.
     *
     * @param id       the id
     * @param pageable the pageable
     * @return the page
     */
    Page<Order> findByCustomerId(Integer id, Pageable pageable);
}
