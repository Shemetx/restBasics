package com.epam.esm.convertor;

import com.epam.esm.domain.User;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserSignUpDto;
import com.epam.esm.dto.UserViewDto;
import com.epam.esm.hateoas.UserHateoasResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Convertor from dto to entity and vise versa
 */
@Component
public class UserDtoConvertor implements RepresentationModelAssembler<User, UserViewDto> {

    private UserHateoasResolver hateoasResolver;

    @Autowired
    public void setHateoasResolver(UserHateoasResolver hateoasResolver) {
        this.hateoasResolver = hateoasResolver;
    }

    /**
     * sign up Dto to entity.
     *
     * @param dto the dto
     * @return the tag
     */
    public User dtoToEntity(UserSignUpDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, User.class);
    }

    @Override
    public UserViewDto toModel(User entity) {
        ModelMapper mapper = new ModelMapper();
        UserViewDto map = mapper.map(entity, UserViewDto.class);
        hateoasResolver.addLinks(map);
        return map;
    }

    @Override
    public CollectionModel<UserViewDto> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserViewDto> userViewDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        hateoasResolver.addLinks(userViewDto, 1, 7);
        return userViewDto;
    }
}
