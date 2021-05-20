package com.epam.esm.hateoas;

import org.springframework.hateoas.CollectionModel;

/**
 * The interface Hateoas to add link to entities
 *
 * @param <T> the type parameter
 */
public interface HateoasModelLinker<T> {

    /**
     * Add links to single entity
     *
     * @param entity the entity to add links
     */
    void addLinks(T entity);

    /**
     * Add links to collection.
     *
     * @param entities the entities to add links
     * @param page     the page
     * @param size     the size
     */
    void addLinks(CollectionModel<T> entities, int page, int size);
}
