package com.epam.esm.controllers;

import com.epam.esm.convertor.GiftCertificateConvertor;
import com.epam.esm.domain.Error;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
     * Sets convertor.
     *
     * @param giftCertificateConvertor the gift certificate convertor
     */
    @Autowired
    public void setConvertor(GiftCertificateConvertor giftCertificateConvertor) {
        this.convertor = giftCertificateConvertor;
    }

    /**
     * Sets gift certificate service.
     *
     * @param giftCertificateService the gift certificate service
     */
    @Autowired
    public void setGiftCertificateService(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Return all certificates to client
     *
     * @return the list
     */
    @GetMapping()
    public List<GiftCertificateDto> index() {
        List<GiftCertificate> dtoList = giftCertificateService.findAll();
        return convertor.entityToDto(dtoList);
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
        return convertor.entityToDto(certificate);
    }

    /**
     *
     *
     * @param dto the dto
     * @return the gift certificate dto
     */
    @PostMapping()
    public GiftCertificateDto create(@RequestBody GiftCertificateDto dto) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificateService.save(giftCertificate);
        GiftCertificate byName = giftCertificateService.findByName(dto.getName());
        giftCertificateService.parseCertificateTags(dto.getTags(), byName.getId());
        return show(byName.getId());
    }

    /**
     * Update gift certificate by id
     *
     * @param dto the dto
     * @param id  the id
     * @return the gift certificate dto
     */
    @PutMapping("/{id}")
    public GiftCertificateDto update(@RequestBody GiftCertificateDto dto, @PathVariable("id") int id) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificate.setId(id);
        giftCertificateService.update(giftCertificate);
        giftCertificateService.parseCertificateTags(dto.getTags(), id);
        return show(id);
    }

    /**
     * Delete list.
     *
     * @param id the id
     * @return the list
     */
    @DeleteMapping("/{id}")
    public List<GiftCertificateDto> delete(@PathVariable("id") int id) {
        giftCertificateService.delete(id);
        List<GiftCertificate> dtoList = giftCertificateService.findAll();
        return convertor.entityToDto(dtoList);
    }


    /**
     * Certificate not found error.
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
     * Show ascending date list.
     *
     * @return the list
     */
    @GetMapping("/ascending/date")
    public List<GiftCertificateDto> showAscendingDate() {
        List<GiftCertificate> certificateList = giftCertificateService.getAscendingDate();
        return convertor.entityToDto(certificateList);
    }

    /**
     * Show descending date list.
     *
     * @return the list
     */
    @GetMapping("/descending/date")
    public List<GiftCertificateDto> showDescendingDate() {
        List<GiftCertificate> certificateList = giftCertificateService.getDescendingDate();
        return convertor.entityToDto(certificateList);
    }

    /**
     * Show ascending name list.
     *
     * @return the list
     */
    @GetMapping("/ascending/name")
    public List<GiftCertificateDto> showAscendingName() {
        List<GiftCertificate> certificateList = giftCertificateService.getAscendingName();
        return convertor.entityToDto(certificateList);
    }

    /**
     * Show descending name list.
     *
     * @return the list
     */
    @GetMapping("/descending/name")
    public List<GiftCertificateDto> showDescendingName() {
        List<GiftCertificate> certificateList = giftCertificateService.getDescendingName();
        return convertor.entityToDto(certificateList);
    }

    /**
     * Show all certificates that found by part of name
     *
     * @param name the name
     * @return the list
     */
    @GetMapping("/name/{name}")
    public List<GiftCertificateDto> showByName(@PathVariable("name") String name) {
        List<GiftCertificate> byPartOfName = giftCertificateService.findByPartOfName(name);
        return convertor.entityToDto(byPartOfName);
    }

    /**
     * Show all certificates that found by part of description
     *
     * @param description the description
     * @return the list
     */
    @GetMapping("/description/{description}")
    public List<GiftCertificateDto> showByDescription(@PathVariable("description") String description) {
        List<GiftCertificate> byPartOfDescr = giftCertificateService.findByPartOfDescription(description);
        return convertor.entityToDto(byPartOfDescr);
    }

    /**
     * Show all certificates by tag
     *
     * @param tag the tag
     * @return the list
     */
    @GetMapping("/tag/{tag}")
    public List<GiftCertificateDto> showByTag(@PathVariable("tag") String tag) {
        List<GiftCertificate> certificateList = giftCertificateService.findAllByTag(tag);
        return convertor.entityToDto(certificateList);
    }


}
