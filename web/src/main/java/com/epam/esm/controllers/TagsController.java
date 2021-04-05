package com.epam.esm.controllers;

import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public List<Tag> index() {
        return tagService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Tag show(@PathVariable("id") int id) {
        return tagService.findById(id);
    }


    @PostMapping()
    public List<Tag> create(@RequestBody Tag tag) {
        tagService.save(tag);
        return tagService.findAll();
    }


    @DeleteMapping("/{id}")
    public List<Tag> delete(@PathVariable("id") int id) {
         tagService.delete(Tag.Builder.newInstance().setId(id).build());
        return tagService.findAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error certificateNotFound(EntityNotFoundException ex) {
        String message = ex.getMessage();
        return new Error(404,message);

    }
}
