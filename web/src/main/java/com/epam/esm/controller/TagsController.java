package com.epam.esm.controller;

import com.epam.esm.convertor.TagConvertor;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Catches users requests by mapping /tags
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    private TagService tagService;
    private TagConvertor convertor;

    /**
     * Sets convertor.
     *
     * @param convertor the convertor
     */
    @Autowired
    public void setConvertor(TagConvertor convertor) {
        this.convertor = convertor;
    }

    /**
     * Sets tag service.
     *
     * @param tagService the tag service
     */
    @Autowired
    public void setTagService(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    /**
     * Return all tags to client
     *
     * @param page the page
     * @param size the size
     * @return the list of all tags
     */
    @GetMapping()
    public CollectionModel<TagDto> index(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "7") int size) {
        Page<Tag> all = tagService.findAll(page, size);
        return convertor.toCollectionModel(all);
    }

    /**
     * Show tag by id
     *
     * @param id the id
     * @return the tag
     */
    @GetMapping(value = "/{id}")
    public TagDto show(@PathVariable("id") int id) {
        Tag byId = tagService.findById(id);
        return convertor.toModel(byId);
    }


    /**
     * Creating new tag
     *
     * @param tag the tag
     * @return list of all tags
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody TagDto tag) {
        Tag tag1 = convertor.dtoToEntity(tag);
        Tag save = tagService.save(tag1);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    /**
     * Deleting tag by his id
     *
     * @param id the id
     * @return blank page
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Find most used tag.
     *
     * @return the response entity
     */
    @GetMapping("/popular")
    public ResponseEntity<TagDto> findMostUsed() {
        Tag mostUsed = tagService.findMostUsed();
        return new ResponseEntity<>(convertor.toModel(mostUsed), HttpStatus.ACCEPTED);
    }
}
