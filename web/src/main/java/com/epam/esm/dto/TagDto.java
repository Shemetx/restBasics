package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;
import java.util.Objects;


/**
 * The type Tag dto entity.
 */
public class TagDto extends RepresentationModel<TagDto> {

    private int id;

    @Size(min = 3, max = 16, message = "Tag name should be between 3 and 16 characters")
    private String name;

    /**
     * Instantiates a new Tag dto.
     */
    public TagDto() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDto tagDto = (TagDto) o;
        return Objects.equals(id, tagDto.id) && Objects.equals(name, tagDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
