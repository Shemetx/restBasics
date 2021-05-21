package com.epam.esm.controller;

import com.epam.esm.convertor.GiftCertificateConvertor;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Catches users requests by mapping /certificates
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {

    private GiftCertificateService giftCertificateService;

    private GiftCertificateConvertor convertor;

    /**
     * Sets gift certificate service.
     *
     * @param giftCertificateService the gift certificate service
     */
    @Autowired
    public void setGiftCertificateService(GiftCertificateServiceImpl giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Sets dto convertor.
     *
     * @param giftCertificateConvertor the gift certificate convertor
     */
    @Autowired
    public void setConvertor(GiftCertificateConvertor giftCertificateConvertor) {
        this.convertor = giftCertificateConvertor;
    }

    /**
     * Return all certificates
     *
     * @param sortType the sort type
     * @param sortBy   the sort by
     * @param page     the page
     * @param size     the size
     * @return the list of all tags
     */
    @GetMapping()
    public CollectionModel<GiftCertificateDto> index(@RequestParam(required = false) String sortType,
                                                     @RequestParam(required = false) String sortBy,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "7") int size) {

        Page<GiftCertificate> dtoList;
        if (sortType != null && sortBy != null) {
            dtoList = giftCertificateService.getSortedList(sortType, sortBy, page, size);
        } else {
            dtoList = giftCertificateService.findAll(page, size);
        }
        return convertor.toCollectionModel(dtoList);
    }

    /**
     * Show gift certificate by id
     *
     * @param id the id
     * @return the gift certificate dto
     */
    @GetMapping("/{id}")
    public GiftCertificateDto show(@PathVariable("id") int id) {
        GiftCertificate certificate = giftCertificateService.findById(id);
        return convertor.toModel(certificate);
    }

    /**
     * Create a new certificate.
     *
     * @param dto the dto
     * @return the gift certificate dto
     */
    @PostMapping("/admin")
    public ResponseEntity<GiftCertificateDto> create(@Valid @RequestBody GiftCertificateDto dto) {
        if(dto.areFieldsNull()){
            throw new IllegalArgumentException("Check body input. Fields must not be null");
        }
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        GiftCertificate save = giftCertificateService.save(giftCertificate);
        return new ResponseEntity<>(convertor.toModel(save), HttpStatus.CREATED);
    }

    /**
     * Update gift certificate by id
     *
     * @param dto the dto
     * @param id  the id
     * @return the gift certificate dto
     */
    @PatchMapping("/admin/{id}")
    public GiftCertificateDto update(@Valid @RequestBody GiftCertificateDto dto, @PathVariable("id") int id) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificate.setId(id);
        giftCertificateService.update(giftCertificate);
        return show(id);
    }

    /**
     * Delete certificate by id.
     *
     * @param id the id
     * @return the list
     */
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Show all certificates that found by part of name
     *
     * @param name the name
     * @param page the page
     * @param size the size
     * @return the list
     */
    @GetMapping("/name/{name}")
    public CollectionModel<GiftCertificateDto> showByName(@PathVariable("name") String name, @RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "7") int size) {
        Page<GiftCertificate> byPartOfName = giftCertificateService.findByPartOfName(name, page, size);
        return convertor.toCollectionModel(byPartOfName);
    }

    /**
     * Show all certificates that found by part of description
     *
     * @param description the description
     * @param page        the page
     * @param size        the size
     * @return the list
     */
    @GetMapping("/description/{description}")
    public CollectionModel<GiftCertificateDto> showByDescription(@PathVariable("description") String description,
                                                                 @RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "7") int size) {
        Page<GiftCertificate> byPartOfDescr = giftCertificateService.findByPartOfDescription(description, page, size);
        return convertor.toCollectionModel(byPartOfDescr);
    }


    /**
     * Show all certificates that have requested tags.
     *
     * @param tags the tags
     * @param page the page
     * @param size the size
     * @return the collection model
     */
    @GetMapping("/tag")
    public CollectionModel<GiftCertificateDto> showByTag(@RequestParam(value = "name") List<String> tags,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "7") int size) {
        Page<GiftCertificate> certificateList = giftCertificateService.findAllByTags(tags, page, size);
        return convertor.toCollectionModel(certificateList);
    }


}
