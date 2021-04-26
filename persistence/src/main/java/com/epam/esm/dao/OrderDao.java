package com.epam.esm.dao;

import com.epam.esm.domain.Order;

import java.util.List;

public interface OrderDao {
    Order save(Order order);
    List<Order> findAll(int page,int size);
    List<Order> findByUserId(int id,int page,int size);

}
