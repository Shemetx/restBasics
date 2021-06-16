package com.epam.esm.controller;

import com.epam.esm.convertor.GiftCertificateConvertor;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.specification.SpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * @param sortType    the sort type
     * @param sortBy      the sort by
     * @param name        the name
     * @param description the description
     * @param page        the page
     * @param size        the size
     * @return the list of all tags
     */
    @GetMapping()
    public CollectionModel<GiftCertificateDto> index(@RequestParam(required = false) String sortType,
                                                     @RequestParam(required = false) String sortBy,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String description,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "7") int size) {

        Specification<GiftCertificate> specification = SpecificationBuilder.build(name, description);
        Page<GiftCertificate> dtoList;
        if (sortType != null && sortBy != null) {
            dtoList = giftCertificateService.getSortedList(specification, sortType, sortBy, page, size);
        } else {
            dtoList = giftCertificateService.findAll(specification, page, size);
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
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftCertificateDto> create(@Valid @RequestBody GiftCertificateDto dto) {
        if (dto.areFieldsNull()) {
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
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
