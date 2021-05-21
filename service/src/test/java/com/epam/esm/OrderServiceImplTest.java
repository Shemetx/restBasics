package com.epam.esm;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.domain.Order;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    final int page = 0;
    final int size = 7;
    final int id = 1;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderDao orderDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdPositive() {
        Order order = new Order();
        when(orderDao.findById(id)).thenReturn(Optional.of(order));
        Order byId = orderService.findById(id);
        assertNotNull(byId);
    }

    @Test
    public void findByIdNegative() {
        when(orderDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            orderService.findById(id);
        });
    }

    @Test
    public void findByUserIdPositive() {
        Order order = new Order();
        List<Order> orderList = new ArrayList<Order>() {{
            add(order);
        }};
        Page<Order> pageOrders = new PageImpl<>(orderList);
        when(orderDao.findByCustomerId(id, PageRequest.of(page,size))).thenReturn(pageOrders);
        Page<Order> byUserId = orderService.findByUserId(id, page, size);
        assertFalse(byUserId.isEmpty());
    }

    @Test
    public void findByUserIdNegative() {
        when(orderDao.findByCustomerId(id, PageRequest.of(page,size))).thenReturn(Page.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            orderService.findByUserId(id, page, size);
        });
    }
}
