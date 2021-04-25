package com.epam.esm.convertor;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Order;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConvertor {

    public OrderDto entityToDto(Order order) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(order, OrderDto.class);
    }

    public List<OrderDto> entityToDto(List<Order> orders) {
        return orders.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Order dtoToEntity(OrderDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Order.class);
    }
}
