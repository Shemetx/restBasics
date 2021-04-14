package com.epam.esm.controllers;

import com.epam.esm.convertor.GiftCertificateConvertor;
import com.epam.esm.domain.Error;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Catches users requests by mapping /certificates
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {
    private GiftCertificateService giftCertificateServiceImpl;
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
     * @param giftCertificateServiceImpl the gift certificate service
     */
    @Autowired
    public void setGiftCertificateService(GiftCertificateServiceImpl giftCertificateServiceImpl) {
        this.giftCertificateServiceImpl = giftCertificateServiceImpl;
    }

    /**
     * Return all certificates to client
     *
     * @param sortType the sort type asc/desc
     * @param sortBy   the sort by name/date
     * @return the list
     */
    @GetMapping()
    public List<GiftCertificateDto> index(@RequestParam(required = false) String sortType,
                                          @RequestParam(required = false) String sortBy) {
        List<GiftCertificate> dtoList;
        if (sortType != null && sortBy != null) {
            dtoList = giftCertificateServiceImpl.getSortedList(sortType,sortBy);
        } else {
            dtoList = giftCertificateServiceImpl.findAll();
        }
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
        GiftCertificate certificate = giftCertificateServiceImpl.findById(id);
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
        giftCertificateServiceImpl.save(giftCertificate);
        GiftCertificate byName = giftCertificateServiceImpl.findByName(dto.getName());
        giftCertificateServiceImpl.parseCertificateTags(dto.getTags(), byName.getId());
        return show(byName.getId());
    }

    /**
     * Update gift certificate by id
     *
     * @param dto the dto
     * @param id  the id
     * @return the gift certificate dto
     */
    @PatchMapping("/{id}")
    public GiftCertificateDto update(@RequestBody GiftCertificateDto dto, @PathVariable("id") int id) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificate.setId(id);
        giftCertificateServiceImpl.update(giftCertificate);
        giftCertificateServiceImpl.parseCertificateTags(dto.getTags(), id);
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
        giftCertificateServiceImpl.delete(id);
        List<GiftCertificate> dtoList = giftCertificateServiceImpl.findAll();
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
     * Show all certificates that found by part of name
     *
     * @param name the name
     * @return the list
     */
    @GetMapping("/name/{name}")
    public List<GiftCertificateDto> showByName(@PathVariable("name") String name) {
        List<GiftCertificate> byPartOfName = giftCertificateServiceImpl.findByPartOfName(name);
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
        List<GiftCertificate> byPartOfDescr = giftCertificateServiceImpl.findByPartOfDescription(description);
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
        List<GiftCertificate> certificateList = giftCertificateServiceImpl.findAllByTag(tag);
        return convertor.entityToDto(certificateList);
    }
    /**
     * Exception handler to catch while certificate already exists
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
