package com.epam.esm.hateoas;

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

    }


    @Override
    public void addLinks(CollectionModel<GiftCertificateDto> entities, int page, int size) {
        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("asc", "name",null,null, page, size))
                .withRel("Ascending sort by name"));

        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("asc", "date", null,null, page, size))
                .withRel("Ascending sort by date"));

        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("desc", "name",null,null, page, size))
                .withRel("Descending sort by name"));

        entities.add(linkTo(methodOn(GiftCertificatesController.class)
                .index("desc", "date",null,null, page, size))
                .withRel("Descending sort by date"));
    }

}
