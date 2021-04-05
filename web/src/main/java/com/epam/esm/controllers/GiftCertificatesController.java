package com.epam.esm.controllers;

import com.epam.esm.convertor.GiftCertificateConvertor;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {
    private GiftCertificateService giftCertificateService;
    private GiftCertificateConvertor convertor;

    @Autowired
    public void setConvertor(GiftCertificateConvertor giftCertificateConvertor) {
        this.convertor = giftCertificateConvertor;
    }

    @Autowired
    public void setGiftCertificateService(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping()
    public List<GiftCertificateDto> index() {
        List<GiftCertificate> dtoList = giftCertificateService.findAll();
        return convertor.entityToDto(dtoList);
    }

    @GetMapping("/{id}")
    public GiftCertificateDto show(@PathVariable("id") int id) {
        GiftCertificate certificate = giftCertificateService.findById(id);
        return convertor.entityToDto(certificate);
    }

    @PostMapping()
    public GiftCertificateDto create(@RequestBody GiftCertificateDto dto) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificateService.save(giftCertificate);
        GiftCertificate byName = giftCertificateService.findByName(dto.getName());
        dto.setId(byName.getId());
        convertor.parseTags(dto);
        return dto;
    }

    @PatchMapping("/{id}")
    public GiftCertificateDto update(@RequestBody GiftCertificateDto dto, @PathVariable("id") int id) {
        GiftCertificate giftCertificate = convertor.dtoToEntity(dto);
        giftCertificateService.update(giftCertificate);
        convertor.parseTags(dto);
        return dto;
    }

    @DeleteMapping("/{id}")
    public List<GiftCertificateDto> delete(@PathVariable("id") int id) {
        giftCertificateService.delete(GiftCertificate.Builder.newInstance().setId(id).build());
        List<GiftCertificate> dtoList = giftCertificateService.findAll();
        return convertor.entityToDto(dtoList);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error certificateNotFound(EntityNotFoundException ex) {
        String message = ex.getMessage();
        return new Error(404,message);

    }

    @GetMapping("/ascending/date")
    public List<GiftCertificateDto> showAscendingDate() {
        List<GiftCertificate> certificateList = giftCertificateService.getAscendingDate();
        return convertor.entityToDto(certificateList);
    }

    @GetMapping("/descending/date")
    public List<GiftCertificateDto> showDescendingDate() {
        List<GiftCertificate> certificateList = giftCertificateService.getDescendingDate();
        return convertor.entityToDto(certificateList);
    }

    @GetMapping("/ascending/name")
    public List<GiftCertificateDto> showAscendingName() {
        List<GiftCertificate> certificateList = giftCertificateService.getAscendingName();
        return convertor.entityToDto(certificateList);
    }
    @GetMapping("/descending/name")
    public List<GiftCertificateDto> showDescendingName() {
        List<GiftCertificate> certificateList = giftCertificateService.getDescendingName();
        return convertor.entityToDto(certificateList);
    }

    @GetMapping("/name/{name}")
    public List<GiftCertificateDto> showByName(@PathVariable("name") String name) {
        List<GiftCertificate> byPartOfName = giftCertificateService.findByPartOfName(name);
        return convertor.entityToDto(byPartOfName);
    }

    @GetMapping("/description/{description}")
    public List<GiftCertificateDto> showByDescription(@PathVariable("description") String description) {
        List<GiftCertificate> byPartOfDescr = giftCertificateService.findByPartOfDescription(description);
        return convertor.entityToDto(byPartOfDescr);
    }

    @GetMapping("/tag/{tag}")
    public List<GiftCertificateDto> showByTag(@PathVariable("tag") String tag) {
        List<GiftCertificate> certificateList = giftCertificateService.findAllByTag(tag);
        return convertor.entityToDto(certificateList);
    }


}
