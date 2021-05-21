package com.epam.esm;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class GiftCertificateServiceImplTest {

    final int page = 0;
    final int size = 7;
    private final GiftCertificate testEntity = new GiftCertificate(1, "Test", "Test description", 3.6f,
            30, LocalDateTime.parse("2020-01-01T12:12:12"), LocalDateTime.parse("2020-03-03T14:14:14"));
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
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(testList);
        when(dao.findByNameContains(testEntity.getName(), PageRequest.of(page, size))).thenReturn(pageGifts);
        Page<GiftCertificate> list = service.findByPartOfName(testEntity.getName(), page, size);
        assertEquals(list, pageGifts);
    }

    @Test
    public void findByPartOfNameNegative() {
        when(dao.findByNameContains(testEntity.getName(), PageRequest.of(page, size))).thenReturn(Page.empty());
        assertThrows(EntityNotFoundException.class, () ->
                service.findByPartOfName(testEntity.getName(), page, size));
    }

    @Test
    public void findByPartOfDescriptionPositive() {
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(testList);
        when(dao.findByDescriptionContains(testEntity.getDescription(), PageRequest.of(page, size))).thenReturn(pageGifts);
        Page<GiftCertificate> list = service.findByPartOfDescription(testEntity.getDescription(), page, size);
        assertEquals(list, pageGifts);
    }

    @Test
    public void findByPartOfDescriptionNegative() {
        when(dao.findByDescriptionContains(testEntity.getDescription(), PageRequest.of(page, size))).thenReturn(Page.empty());
        assertThrows(EntityNotFoundException.class, () ->
                service.findByPartOfDescription(testEntity.getDescription(), page, size));
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
        List<String> tagNames = new ArrayList<String>() {{
            add("Test");
        }};

        List<Tag> tagList = new ArrayList<Tag>() {{
            add(tagTest);
        }};
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(testList);
        when(tagServiceImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findBySeveralTags(tagList, PageRequest.of(page, size))).thenReturn(pageGifts);
        Page<GiftCertificate> allByTag = service.findAllByTags(tagNames, page, size);
        assertEquals(allByTag, pageGifts);
    }

    @Test
    public void findAllByTagNegative() {
        Tag tagTest = new Tag(1, "Test");
        List<String> tagNames = new ArrayList<String>() {{
            add("Test");
        }};

        List<Tag> tagList = new ArrayList<Tag>() {{
            add(tagTest);
        }};
        when(tagServiceImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findBySeveralTags(tagList, PageRequest.of(page, size))).thenReturn(Page.empty());
        assertThrows(EntityNotFoundException.class, () ->
                service.findAllByTags(tagNames, page, size));

    }

    @Test
    public void ascendingDateSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"));

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(PageRequest.of(page, size, Sort.by("createDate").ascending()))).thenReturn(pageGifts);
        Page<GiftCertificate> ascendingDate = service.getSortedList("asc", "date", page, size);
        assertEquals(pageGifts, ascendingDate);
    }

    @Test
    public void descendingDateSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"));

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(PageRequest.of(page, size, Sort.by("createDate").descending()))).thenReturn(pageGifts);

        Page<GiftCertificate> ascendingDate = service.getSortedList("desc", "date", page, size);
        assertEquals(pageGifts, ascendingDate);
    }

    @Test
    public void ascendingNameSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Zurado");

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(testEntity);
            add(certificate);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(PageRequest.of(page, size, Sort.by("name").ascending()))).thenReturn(pageGifts);

        Page<GiftCertificate> ascendingDate = service.getSortedList("asc", "name", page, size);
        assertEquals(pageGifts, ascendingDate);
    }

    @Test
    public void descendingNameSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Zurado");

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        Page<GiftCertificate> pageGifts = new PageImpl<>(expected);
        when(dao.findAll(PageRequest.of(page, size, Sort.by("name").descending()))).thenReturn(pageGifts);

        Page<GiftCertificate> ascendingDate = service.getSortedList("desc", "name", page, size);
        assertEquals(pageGifts, ascendingDate);
    }
}
