package com.epam.esm.controllers;

import com.epam.esm.domain.Error;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.TagServiceImpl;
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

    private TagService tagServiceImpl;

    /**
     * Sets tag service.
     *
     * @param tagServiceImpl the tag service
     */
    @Autowired
    public void setTagService(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    /**
     * Return all tags to client
     *
     * @return the list
     */
    @GetMapping()
    public List<Tag> index() {
        return tagServiceImpl.findAll();
    }

    /**
     * Show tag by id
     *
     * @param id the id
     * @return the tag
     */
    @GetMapping(value = "/{id}")
    public Tag show(@PathVariable("id") int id) {
        return tagServiceImpl.findById(id);
    }


    /**
     * Creating new tag
     *
     * @param tag the tag
     * @return list of all tags
     */
    @PostMapping()
    public List<Tag> create(@RequestBody Tag tag) {
        tagServiceImpl.save(tag);
        return tagServiceImpl.findAll();
    }


    /**
     * Deleting tag by his id
     *
     * @param id the id
     * @return list of all tags
     */
    @DeleteMapping("/{id}")
    public List<Tag> delete(@PathVariable("id") int id) {
        tagServiceImpl.delete(id);
        return tagServiceImpl.findAll();
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
    /**
     * Exception handler to catch while tag already exists
     *
     * @param ex the ex
     * @return the error
     */
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error certificateNotFound(EntityAlreadyExistsException ex) {
        String message = ex.getMessage();
        return new Error(409, message);
    }
}
