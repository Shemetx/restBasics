package com.epam.esm.controller;

import com.epam.esm.domain.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagServiceImpl tagService) {
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
    public ResponseEntity<?> create(@Valid @RequestBody Tag tag) {
        Tag save = tagService.save(tag);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    /**
     * Deleting tag by his id
     *
     * @param id the id
     * @return list of all tags
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/popular")
    public Tag findMostUsed() {
        return tagService.findMostUsed();
    }
}
