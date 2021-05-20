package com.epam.esm.convertor;

import com.epam.esm.domain.User;
import com.epam.esm.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConvertor {
    /**
     * Dto to entity.
     *
     * @param dto the dto
     * @return the tag
     */
    public User dtoToEntity(UserDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, User.class);
    }
}
