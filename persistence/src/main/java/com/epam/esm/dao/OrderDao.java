package com.epam.esm.dao;

import com.epam.esm.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDao extends JpaRepository<Order,Integer> {

    Page<Order> findByCustomerId(Integer id, Pageable pageable);
}
