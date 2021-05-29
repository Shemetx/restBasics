package com.epam.esm.hateoas;

import com.epam.esm.controller.OrdersController;
import com.epam.esm.dto.UserViewDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * The type User hateoas link resolver.
 */
@Component
public class UserHateoasResolver implements HateoasModelLinker<UserViewDto> {

    @Override
    public void addLinks(UserViewDto entity) {
       entity.add(linkTo(methodOn(OrdersController.class)
       .userOrders(entity.getId(),1,7))
               .withSelfRel());

    }

    @Override
    public void addLinks(CollectionModel<UserViewDto> entities, int page, int size) {
    //not implemented
    }
}
