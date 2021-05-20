package com.epam.esm.convertor;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.hateoas.CertificateHateoasResolver;
import com.epam.esm.hateoas.HateoasModelLinker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Gift certificate converts from DTO to entity and vice versa.
 */
@Component
public class GiftCertificateConvertor implements RepresentationModelAssembler<GiftCertificate, GiftCertificateDto> {

    private HateoasModelLinker<GiftCertificateDto> hateoasResolver;
    private TagConvertor tagConvertor;

    /**
     * Sets tag convertor.
     *
     * @param tagConvertor the tag convertor
     */
    @Autowired
    public void setTagConvertor(TagConvertor tagConvertor) {
        this.tagConvertor = tagConvertor;
    }

    /**
     * Sets hateoas resolver.
     *
     * @param hateoasResolver the hateoas resolver
     */
    @Autowired
    public void setHateoasResolver(CertificateHateoasResolver hateoasResolver) {
        this.hateoasResolver = hateoasResolver;
    }

    /**
     * Dto to entity GiftCertificate.
     *
     * @param dto the dto
     * @return the gift certificate
     */
    public GiftCertificate dtoToEntity(GiftCertificateDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, GiftCertificate.class);
    }

    @Override
    public GiftCertificateDto toModel(GiftCertificate entity) {
        Set<TagDto> collect = entity.getTags().stream().map(tag -> tagConvertor.toModel(tag)).collect(Collectors.toSet());
        ModelMapper mapper = new ModelMapper();
        GiftCertificateDto map = mapper.map(entity, GiftCertificateDto.class);
        map.setTags(collect);
        hateoasResolver.addLinks(map);
        return map;
    }

    @Override
    public CollectionModel<GiftCertificateDto> toCollectionModel(Iterable<? extends GiftCertificate> entities) {
        CollectionModel<GiftCertificateDto> giftCertificateDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        hateoasResolver.addLinks(giftCertificateDtos, 1, 7);
        return giftCertificateDtos;
    }
}
