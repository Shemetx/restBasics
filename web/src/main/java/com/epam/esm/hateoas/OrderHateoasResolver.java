package com.epam.esm.hateoas;

import com.epam.esm.controller.OrdersController;
import com.epam.esm.dto.OrderDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Order hateoas link resolver.
 */
@Component
public class OrderHateoasResolver implements HateoasModelLinker<OrderDto> {

    @Override
    public void addLinks(OrderDto entity) {
        entity.add(linkTo(methodOn(OrdersController.class)
                .show(entity.getId()))
                .withSelfRel());

        entity.add(linkTo(methodOn(OrdersController.class)
                .userOrders(entity.getCustomer().getId(), 1, 7))
                .withRel("User orders"));
    }

    @Override
    public void addLinks(CollectionModel<OrderDto> entities, int page, int size) {
        entities.add(linkTo(methodOn(OrdersController.class).index(page, size)).withSelfRel());
    }
}
