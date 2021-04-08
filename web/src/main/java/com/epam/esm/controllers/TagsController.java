package com.epam.esm.controllers;

import com.epam.esm.domain.Error;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Catches users requests by mapping /tags
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    private TagService tagService;

    /**
     * Sets tag service.
     *
     * @param tagService the tag service
     */
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Return all tags to client
     *
     * @return the list
     */
    @GetMapping()
    public List<Tag> index() {
        return tagService.findAll();
    }

    /**
     * Show tag by id
     *
     * @param id the id
     * @return the tag
     */
    @GetMapping(value = "/{id}")
    public Tag show(@PathVariable("id") int id) {
        return tagService.findById(id);
    }


    /**
     * Creating new tag
     *
     * @param tag the tag
     * @return list of all tags
     */
    @PostMapping()
    public List<Tag> create(@RequestBody Tag tag) {
        tagService.save(tag);
        return tagService.findAll();
    }


    /**
     * Deleting tag by his id
     *
     * @param id the id
     * @return list of all tags
     */
    @DeleteMapping("/{id}")
    public List<Tag> delete(@PathVariable("id") int id) {
        tagService.delete(id);
        return tagService.findAll();
    }

    /**
     * Exception handler to catch while tag now found
     *
     * @param ex the ex
     * @return the error
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error certificateNotFound(EntityNotFoundException ex) {
        String message = ex.getMessage();
        return new Error(404, message);

    }
}
