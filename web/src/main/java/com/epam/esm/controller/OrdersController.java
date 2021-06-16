package com.epam.esm.controller;

import com.epam.esm.convertor.OrderConvertor;
import com.epam.esm.convertor.OrderUserViewConvertor;
import com.epam.esm.domain.Order;
import com.epam.esm.domain.User;
import com.epam.esm.dto.OrderUserViewDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Catches users requests by mapping /orders
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private OrderService orderService;
    private OrderConvertor orderConvertor;
    private UserService userService;
    private OrderUserViewConvertor userViewConvertor;

    @Autowired
    public void setUserViewConvertor(OrderUserViewConvertor userViewConvertor) {
        this.userViewConvertor = userViewConvertor;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Sets order convertor.
     *
     * @param orderConvertor the order convertor
     */
    @Autowired
    public void setOrderConvertor(OrderConvertor orderConvertor) {
        this.orderConvertor = orderConvertor;
    }

    /**
     * Sets order service.
     *
     * @param orderService the order service
     */
    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * Buy certificate.
     *
     * @param dto the dto
     * @return the order dto
     */
    @PostMapping("/buy")
    public OrderDto buyCertificate(@RequestBody OrderUserViewDto dto, HttpServletRequest request) {
        User customer = userService.getIdFromHttpRequest(request);
        Order order = orderConvertor.dtoToEntity(dto);
        order.setCustomer(customer);
        Order save = orderService.save(order);
        return orderConvertor.toModel(save);
    }

    /**
     * Show all orders.
     *
     * @param page the page
     * @param size the size
     * @return all orders
     */
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public CollectionModel<OrderDto> index(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "7") int size) {
        Page<Order> all = orderService.findAll(page, size);
        return orderConvertor.toCollectionModel(all);
    }

    /**
     * Show all user orders by user id.
     *
     * @param id   the id
     * @param page the page
     * @param size the size
     * @return all user orders
     */
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CollectionModel<OrderDto> userOrders(@PathVariable int id,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size) {
        Page<Order> byUserId = orderService.findByUserId(id, page, size);
        return orderConvertor.toCollectionModel(byUserId);
    }

    @GetMapping("/cart")
    public CollectionModel<OrderUserViewDto> userCart(HttpServletRequest request,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size) {
        User customer = userService.getIdFromHttpRequest(request);
        Page<Order> byUserId = orderService.findByUserId(customer.getId(), page, size);
        return userViewConvertor.toCollectionModel(byUserId);
    }

    /**
     * Show order by id.
     *
     * @param id the id
     * @return the order dto
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderDto show(@PathVariable int id) {
        Order byId = orderService.findById(id);
        return orderConvertor.toModel(byId);
    }
}
