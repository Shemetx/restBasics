package com.epam.esm.controller;

import com.epam.esm.convertor.GiftCertificateConvertor;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {

    private GiftCertificateService giftCertificateService;

    private GiftCertificateConvertor convertor;

    @Autowired
    public void setGiftCertificateService(GiftCertificateServiceImpl giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }
    /**
     * Sets convertor.
     *
     * @param giftCertificateConvertor the gift certificate convertor
     */
    @Autowired
    public void setConvertor(GiftCertificateConvertor giftCertificateConvertor) {
        this.convertor = giftCertificateConvertor;
    }

    @GetMapping()
    public List<GiftCertificateDto> index(@RequestParam(required = false) String sortType,
                                          @RequestParam(required = false) String sortBy) {

        List<GiftCertificate> dtoList;
        if (sortType != null && sortBy != null) {
            dtoList = giftCertificateService.getSortedList(sortType,sortBy);
        } else {
            dtoList = giftCertificateService.findAll();
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
    public ResponseEntity<GiftCertificateDto> create(@Valid @RequestBody GiftCertificateDto dto) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        GiftCertificate save = giftCertificateService.save(giftCertificate);
        return new ResponseEntity<>(convertor.entityToDto(save),HttpStatus.CREATED);
    }

    /**
     * Update gift certificate by id
     *
     * @param dto the dto
     * @param id  the id
     * @return the gift certificate dto
     */
    @PutMapping("/{id}")
    public GiftCertificateDto update(@Valid @RequestBody GiftCertificateDto dto, @PathVariable("id") int id) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificate.setId(id);
        giftCertificateService.update(giftCertificate);
        return show(id);
    }
    /**
     * Delete list.
     *
     * @param id the id
     * @return the list
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
