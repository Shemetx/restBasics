package com.epam.esm;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.specification.SpecificationBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class GiftCertificateServiceImplTest {

    public static final int page = 0;
    public static final int size = 7;
    private final GiftCertificate testEntity = new GiftCertificate(1, "Test", "Test description", 3.6f,
            30, LocalDateTime.parse("2020-01-01T12:12:12"), LocalDateTime.parse("2020-03-03T14:14:14"));
    private final Specification<GiftCertificate> specificationName = SpecificationBuilder.build("est", null);
    private final Specification<GiftCertificate> specificationDescription = SpecificationBuilder.build(null, "descr");
    private final Specification<GiftCertificate> specificationEmpty = SpecificationBuilder.build(null, null);
    @InjectMocks
    @Spy
    private GiftCertificateServiceImpl service;
    @Mock
    private GiftCertificateDao dao;
    @Mock
    private TagService tagServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByPartOfNamePositive() {
        List<GiftCertificate> testList = new ArrayList<>();
        testList.add(testEntity);
        Page<GiftCertificate> pageGifts = new PageImpl<>(testList);
        when(dao.findAll(specificationName, PageRequest.of(page, size))).thenReturn(pageGifts);
        Page<GiftCertificate> list = service.findAll(specificationName, page, size);
        assertEquals(list, pageGifts);
    }

    @Test
    public void findByPartOfNameNegative() {
        when(dao.findAll(specificationName, PageRequest.of(page, size))).thenReturn(Page.empty());
        Page<GiftCertificate> actual = service.findAll(specificationName,page,size);
        assertEquals(actual,Page.empty());
    }

    @Test
    public void findByPartOfDescriptionPositive() {
        List<GiftCertificate> testList = new ArrayList<>();
        testList.add(testEntity);
        Page<GiftCertificate> pageGifts = new PageImpl<>(testList);
        when(dao.findAll(specificationDescription, PageRequest.of(page, size))).thenReturn(pageGifts);
        Page<GiftCertificate> list = service.findAll(specificationDescription, page, size);
        assertEquals(list, pageGifts);
    }

    @Test
    public void findByPartOfDescriptionNegative() {
        when(dao.findAll(specificationDescription, PageRequest.of(page, size))).thenReturn(Page.empty());
        Page<GiftCertificate> actual = service.findAll(specificationDescription,page,size);
        assertEquals(actual,Page.empty());
    }

    @Test
    public void findByIdPositive() {
        when(dao.findById(testEntity.getId())).thenReturn(Optional.of(testEntity));
        GiftCertificate certificate = service.findById(testEntity.getId());
        assertEquals(certificate, testEntity);
    }

    @Test
    public void findByIdNegative() {
        when(dao.findById(testEntity.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                service.findById(testEntity.getId()));
    }


    @Test
    public void findAllByTagPositive() {
        Tag tagTest = new Tag(1, "Test");
        List<String> tagNames = new ArrayList<>();
        tagNames.add("Test");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(tagTest);
        List<GiftCertificate> testList = new ArrayList<>();
        testList.add(testEntity);

        Page<GiftCertificate> pageGifts = new PageImpl<>(testList);
        when(tagServiceImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findBySeveralTags(tagList, PageRequest.of(page, size))).thenReturn(pageGifts);
        Page<GiftCertificate> allByTag = service.findAllByTags(tagNames, page, size);
        assertEquals(allByTag, pageGifts);
    }

    @Test
    public void findAllByTagNegative() {
        Tag tagTest = new Tag(1, "Test");
        List<String> tagNames = new ArrayList<>();
        tagNames.add("Test");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(tagTest);
        when(tagServiceImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findBySeveralTags(tagList, PageRequest.of(page, size))).thenReturn(Page.empty());
        assertThrows(EntityNotFoundException.class, () ->
                service.findAllByTags(tagNames, page, size));

    }

    @Test
    public void ascendingDateSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"));

        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(certificate);
        expected.add(testEntity);
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(specificationEmpty,PageRequest.of(page, size, Sort.by("createDate").ascending()))).thenReturn(pageGifts);
        Page<GiftCertificate> ascendingDate = service.getSortedList(specificationEmpty, "asc", "date", page, size);
        assertEquals(pageGifts, ascendingDate);
    }

    @Test
    public void descendingDateSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"));

        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(certificate);
        expected.add(testEntity);
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(specificationEmpty,PageRequest.of(page, size, Sort.by("createDate").descending()))).thenReturn(pageGifts);

        Page<GiftCertificate> ascendingDate = service.getSortedList(specificationEmpty, "desc", "date", page, size);
        assertEquals(pageGifts, ascendingDate);
    }

    @Test
    public void ascendingNameSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Zurado");

        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(testEntity);
        expected.add(certificate);
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(specificationEmpty,PageRequest.of(page, size, Sort.by("name").ascending()))).thenReturn(pageGifts);

        Page<GiftCertificate> ascendingDate = service.getSortedList(specificationEmpty, "asc", "name", page, size);
        assertEquals(pageGifts, ascendingDate);
    }

    @Test
    public void descendingNameSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Zurado");

        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(certificate);
        expected.add(testEntity);
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(specificationEmpty,PageRequest.of(page, size, Sort.by("name").descending()))).thenReturn(pageGifts);

        Page<GiftCertificate> ascendingDate = service.getSortedList(specificationEmpty, "desc", "name", page, size);
        assertEquals(pageGifts, ascendingDate);
    }
}
