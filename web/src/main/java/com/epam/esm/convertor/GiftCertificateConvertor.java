package com.epam.esm.convertor;

import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.CertificateTagsService;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GiftCertificateConvertor {

    private CertificateTagsService certificateTagsService;
    private TagService tagService;
    private GiftCertificateService certificateService;

    @Autowired
    public void setCertificateService(GiftCertificateService giftCertificateService) {
        this.certificateService = giftCertificateService;
    }

    @Autowired
    public void setTagsDao(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
   public void setCertificateTagsService(CertificateTagsService certificateTagsService) {
        this.certificateTagsService = certificateTagsService;
   }

    public GiftCertificateDto entityToDto(GiftCertificate giftCertificate) {
        ModelMapper mapper = new ModelMapper();
        GiftCertificateDto map = mapper.map(giftCertificate, GiftCertificateDto.class);
        map.setTags(certificateService.findCertificateTags(giftCertificate.getId()));
        return map;
    }

    public List<GiftCertificateDto> entityToDto(List<GiftCertificate> giftCertificates) {
        return giftCertificates.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public GiftCertificate dtoToEntity(GiftCertificateDto dto) {
        ModelMapper mapper = new ModelMapper();
        GiftCertificate certificate = mapper.map(dto, GiftCertificate.class);
        certificate.setCreateDate(LocalDateTime.now());
        certificate.setLastUpdateDate(LocalDateTime.now());
        return certificate;
    }

    public void parseTags(GiftCertificateDto dto) {
        Set<Tag> tagSet = dto.getTags();
        List<Tag> currentTags = tagService.findAll();
        for (Tag tag : tagSet) {
            Optional<Tag> byId = currentTags.stream().filter(x -> tag.getId() == x.getId()).findFirst();
            if (byId.isPresent()) {
                if (!tag.getName().equals(byId.get().getName())) {
                    tagService.update(tag);
                }
            } else {
                Optional<Tag> byName = currentTags.stream().filter(x -> tag.getName().equals(x.getName())).findFirst();
                if (!byName.isPresent()) {
                    tagService.save(tag);
                }
                CertificatesTags certificatesTags = CertificatesTags.Builder.newInstance()
                .setCertificateId(dto.getId())
                .setTagId(tagService.findByName(tag.getName()).getId())
                .build();
                certificateTagsService.save(certificatesTags);
            }
        }
    }

    public List<GiftCertificate> dtoToEntity(List<GiftCertificateDto> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
