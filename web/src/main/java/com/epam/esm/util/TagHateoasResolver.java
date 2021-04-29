package com.epam.esm.util;

import com.epam.esm.controller.TagsController;
import com.epam.esm.dto.TagDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Tag hateoas link resolver.
 */
@Component
public class TagHateoasResolver implements HateoasModelLinker<TagDto> {

    @Override
    public void addLinks(TagDto resource) {
        resource.add(linkTo(methodOn(TagsController.class)
                .show(resource.getId()))
                .withSelfRel());
        resource.add(linkTo(methodOn(TagsController.class)
                .delete(resource.getId()))
                .withRel("delete"));
    }

    @Override
    public void addLinks(CollectionModel<TagDto> resources, int page, int size) {
        resources.add(linkTo(methodOn(TagsController.class).index(page, size)).withSelfRel());
        resources.add(linkTo(methodOn(TagsController.class).findMostUsed()).withRel("Most used tag"));
    }

}
