package com.epam.esm;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.impl.OrderDaoImpl;
import com.epam.esm.domain.Order;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.impl.OrderServiceImpl;
import com.epam.esm.util.PageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderDaoImpl orderDao;

    @Mock
    private PageUtil pageUtil;

    final int page = 0;
    final int size = 7;
    final int id = 1;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(pageUtil.getCorrectPage(page,size)).thenReturn(0);

    }

    @Test
    public void findByIdPositive() {
        Order order = new Order();
        when(orderDao.findById(id)).thenReturn(order);
        Order byId = orderService.findById(id);
        assertNotNull(byId);
    }

    @Test
    public void findByIdNegative() {
        when(orderDao.findById(id)).thenReturn(null);
        assertThrows(EntityNotFoundException.class,() -> {
            orderService.findById(id);
        });
    }

    @Test
    public void findByUserIdPositive() {
        Order order = new Order();
        List<Order> orderList = new ArrayList<Order>() {{
            add(order);
        }};
        when(orderDao.findByUserId(id,page,size)).thenReturn(orderList);
        List<Order> byUserId = orderService.findByUserId(id, page, size);
        assertFalse(byUserId.isEmpty());
    }

    @Test
    public void findByUserIdNegative() {
        when(orderDao.findByUserId(id,page,size)).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            orderService.findByUserId(id, page, size);
        });
    }
}
