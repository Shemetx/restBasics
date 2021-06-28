package com.epam.esm.convertor;

import com.epam.esm.domain.Order;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderUserViewDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.UserViewDto;
import com.epam.esm.hateoas.HateoasModelLinker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Order converts from DTO to entity and vice versa.
 */
@Component
public class OrderConvertor implements RepresentationModelAssembler<Order, OrderDto> {

    private HateoasModelLinker<OrderDto> orderResolver;
    private GiftCertificateConvertor certificateConvertor;
    private UserDtoConvertor userConvertor;

    @Autowired
    public void setUserConvertor(UserDtoConvertor userConvertor) {
        this.userConvertor = userConvertor;
    }

    /**
     * Sets certificate convertor.
     *
     * @param certificateConvertor the certificate convertor
     */
    @Autowired
    public void setCertificateConvertor(GiftCertificateConvertor certificateConvertor) {
        this.certificateConvertor = certificateConvertor;
    }

    /**
     * Sets order resolver.
     *
     * @param orderResolver the order resolver
     */
    @Autowired
    public void setOrderResolver(HateoasModelLinker<OrderDto> orderResolver) {
        this.orderResolver = orderResolver;
    }

    /**
     * Dto to entity order.
     *
     * @param dto the dto
     * @return the order
     */
    public Order dtoToEntity(OrderUserViewDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Order.class);
    }

    @Override
    public OrderDto toModel(Order entity) {
        UserViewDto userViewDto = userConvertor.toModel(entity.getCustomer());

        Set<GiftCertificateDto> collect = entity.getCertificates().stream()
                .map(certificateConvertor::toModel)
                .collect(Collectors.toSet());

        ModelMapper mapper = new ModelMapper();
        OrderDto map = mapper.map(entity, OrderDto.class);
        orderResolver.addLinks(map);
        map.setCertificates(collect);
        map.setCustomer(userViewDto);
        return map;
    }

    @Override
    public CollectionModel<OrderDto> toCollectionModel(Iterable<? extends Order> entities) {
        CollectionModel<OrderDto> orderDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        orderResolver.addLinks(orderDtos, 1, 7);
        return orderDtos;
    }
}
