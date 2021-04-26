package com.epam.esm.controller;

import com.epam.esm.convertor.OrderConvertor;
import com.epam.esm.domain.Order;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private OrderConvertor orderConvertor;

    @Autowired
    public void setOrderConvertor(OrderConvertor orderConvertor) {
        this.orderConvertor = orderConvertor;
    }

    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public OrderDto buyCertificate(@RequestBody OrderDto dto) {
        Order order = orderConvertor.dtoToEntity(dto);
        Order save = orderService.save(order);
        return orderConvertor.entityToDto(save);
    }

    @GetMapping()
    public List<OrderDto> index(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "7") int size) {
        List<Order> all = orderService.findAll(page, size);
        return orderConvertor.entityToDto(all);
    }

    @GetMapping("/user/{id}")
    public List<OrderDto> userOrders(@PathVariable int id,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "7") int size) {
        List<Order> byUserId = orderService.findByUserId(id, page, size);
        return orderConvertor.entityToDto(byUserId);
    }
}
