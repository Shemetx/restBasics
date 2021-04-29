package com.epam.esm.util;

import com.epam.esm.controller.GiftCertificatesController;
import com.epam.esm.dto.GiftCertificateDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Certificate hateoas link resolver.
 */
@Component
public class CertificateHateoasResolver implements HateoasModelLinker<GiftCertificateDto> {

    @Override
    public void addLinks(GiftCertificateDto resource) {
        resource.add(linkTo(methodOn(GiftCertificatesController.class)
                .show(resource.getId()))
                .withSelfRel());

        resource.add(linkTo(methodOn(GiftCertificatesController.class)
                .delete(resource.getId()))
                .withRel("delete"));
    }


    @Override
    public void addLinks(CollectionModel<GiftCertificateDto> entities, int page, int size) {
        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("asc", "name", page, size))
                .withRel("Ascending sort by name"));

        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("asc", "date", page, size))
                .withRel("Ascending sort by date"));

        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("desc", "name", page, size))
                .withRel("Descending sort by name"));

        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("desc", "date", page, size))
                .withRel("Descending sort by date"));
    }

}
