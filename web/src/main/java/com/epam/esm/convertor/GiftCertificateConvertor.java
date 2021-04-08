package com.epam.esm.convertor;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts from DTO to entity GiftCertificate and visa versa
 */
@Component
public class GiftCertificateConvertor {


    private GiftCertificateService certificateService;

    /**
     * Sets certificate service.
     *
     * @param giftCertificateService the gift certificate service
     */
    @Autowired
    public void setCertificateService(GiftCertificateService giftCertificateService) {
        this.certificateService = giftCertificateService;
    }

    /**
     * Entity to dto gift certificate.
     *
     * @param giftCertificate the gift certificate
     * @return the gift certificate dto
     */
    public GiftCertificateDto entityToDto(GiftCertificate giftCertificate) {
        ModelMapper mapper = new ModelMapper();
        GiftCertificateDto map = mapper.map(giftCertificate, GiftCertificateDto.class);
        map.setTags(certificateService.findCertificateTags(giftCertificate.getId()));
        return map;
    }

    /**
     * List of entities to list dto
     *
     * @param giftCertificates the gift certificates
     * @return the list
     */
    public List<GiftCertificateDto> entityToDto(List<GiftCertificate> giftCertificates) {
        return giftCertificates.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * Dto to entity GiftCertificate.
     *
     * @param dto the dto
     * @return the gift certificate
     */
    public GiftCertificate dtoToEntity(GiftCertificateDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, GiftCertificate.class);
    }

}
