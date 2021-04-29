package com.epam.esm.convertor;

import com.epam.esm.domain.Tag;
import com.epam.esm.dto.TagDto;
import com.epam.esm.util.TagHateoasResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * The type Tag converts from DTO to Entity and vice versa
 */
@Component
public class TagConvertor implements RepresentationModelAssembler<Tag, TagDto> {

    private TagHateoasResolver hateoasResolver;

    /**
     * Sets hateoas resolver.
     *
     * @param hateoasResolver the hateoas resolver
     */
    @Autowired
    public void setHateoasResolver(TagHateoasResolver hateoasResolver) {
        this.hateoasResolver = hateoasResolver;
    }

    /**
     * Dto to entity.
     *
     * @param dto the dto
     * @return the tag
     */
    public Tag dtoToEntity(TagDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Tag.class);
    }

    @Override
    public TagDto toModel(Tag entity) {
        ModelMapper mapper = new ModelMapper();
        TagDto map = mapper.map(entity, TagDto.class);
        hateoasResolver.addLinks(map);
        return map;
    }

    @Override
    public CollectionModel<TagDto> toCollectionModel(Iterable<? extends Tag> entities) {
        CollectionModel<TagDto> tagDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        hateoasResolver.addLinks(tagDtos, 1, 7);
        return tagDtos;
    }
}
