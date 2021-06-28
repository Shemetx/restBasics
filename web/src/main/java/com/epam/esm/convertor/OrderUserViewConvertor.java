package com.epam.esm.convertor;

import com.epam.esm.domain.Order;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.OrderUserViewDto;
import com.epam.esm.dto.UserViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderUserViewConvertor implements RepresentationModelAssembler<Order, OrderUserViewDto> {

    private GiftCertificateConvertor certificateConvertor;

    @Autowired
    public void setCertificateConvertor(GiftCertificateConvertor certificateConvertor) {
        this.certificateConvertor = certificateConvertor;
    }

    @Override
    public OrderUserViewDto toModel(Order entity) {
        Set<GiftCertificateDto> collect = entity.getCertificates().stream()
                .map(certificateConvertor::toModel)
                .collect(Collectors.toSet());

        ModelMapper mapper = new ModelMapper();
        OrderUserViewDto map = mapper.map(entity, OrderUserViewDto.class);
        map.setCertificates(collect);
        return map;
    }

    @Override
    public CollectionModel<OrderUserViewDto> toCollectionModel(Iterable<? extends Order> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
