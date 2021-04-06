package com.epam.esm;

import com.epam.esm.dao.impl.GiftCertificateDao;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.CertificateTagsService;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GiftCertificateServiceTest {

    @InjectMocks
    @Spy
    private GiftCertificateService service;

    @Mock
    private GiftCertificateDao dao;

    @Mock
    private TagService tagService;

    @Mock
    private CertificateTagsService certificateTagsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private  final GiftCertificate testEntity = GiftCertificate.Builder.newInstance()
            .setId(1)
            .setName("Test")
            .setDescription("Test description")
            .setPrice(3.6f)
            .setDuration(30)
            .setCreateDate(LocalDateTime.parse("2020-01-01T12:12:12"))
            .setLastUpdateDate(LocalDateTime.parse("2020-03-03T14:14:14"))
            .build();

    @Test
    public void findByPartOfNamePositive() {
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
      when(dao.findByPartOfName(testEntity.getName())).thenReturn(testList);
        List<GiftCertificate> list = service.findByPartOfName(testEntity.getName());
        assertEquals(list,testList);
    }
    @Test
    public void findByPartOfNameNegative() {
        when(dao.findByPartOfName(testEntity.getName())).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            service.findByPartOfName(testEntity.getName());
        });
    }

    @Test
    public void findByPartOfDescriptionPositive() {
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        when(dao.findByPartOfDescription(testEntity.getDescription())).thenReturn(testList);
        List<GiftCertificate> list = service.findByPartOfDescription(testEntity.getDescription());
        assertEquals(list,testList);
    }
    @Test
    public void findByPartOfDescriptionNegative() {
        when(dao.findByPartOfDescription(testEntity.getDescription())).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            service.findByPartOfName(testEntity.getDescription());
        });
    }

    @Test
    public void findByIdPositive() {
        when(dao.findById(testEntity.getId())).thenReturn(testEntity);
        GiftCertificate certificate = service.findById(testEntity.getId());
        assertEquals(certificate,testEntity);
    }

    @Test
    public void findByIdNegative() {
        when(dao.findById(testEntity.getId())).thenReturn(null);
        assertThrows(EntityNotFoundException.class,() -> {
            service.findById(testEntity.getId());
        });
    }

    @Test
    public void findByNamePositive() {
        when(dao.findByName(testEntity.getName())).thenReturn(testEntity);
        GiftCertificate certificate = service.findByName(testEntity.getName());
        assertEquals(certificate,testEntity);
    }

    @Test
    public void findByNameNegative() {
        when(dao.findByName(testEntity.getName())).thenReturn(null);
        assertThrows(EntityNotFoundException.class,() -> {
            service.findByName(testEntity.getName());
        });
    }


    @Test
    public void findAllByTagPositive() {
        Tag tagTest = Tag.Builder.newInstance()
                .setId(1)
                .setName("Test")
                .build();

        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};

        when(tagService.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findByTagId(tagTest.getId())).thenReturn(testList);
        List<GiftCertificate> allByTag = service.findAllByTag(tagTest.getName());
        assertEquals(allByTag,testList);
    }

    @Test
    public void findAllByTagNegative() {
        Tag tagTest = Tag.Builder.newInstance()
                .setId(1)
                .setName("Test")
                .build();

        when(tagService.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findByTagId(tagTest.getId())).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            service.findAllByTag(tagTest.getName()); });

    }

    @Test
    public void ascendingDateSortPositive() {
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"))
                .build();
        List<GiftCertificate> current = new ArrayList<GiftCertificate>() {{
           add(certificate);
            add(testEntity);
        }};
        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(testEntity);
            add(certificate);
        }};
        Mockito.doReturn(current).when(service).findAll();

        List<GiftCertificate> ascendingDate = service.getAscendingDate();
        assertEquals(expected,ascendingDate);
    }

    @Test
    public void descendingDateSortPositive() {
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"))
                .build();
        List<GiftCertificate> current = new ArrayList<GiftCertificate>() {{
            add(testEntity);
            add(certificate);
        }};
        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        Mockito.doReturn(current).when(service).findAll();

        List<GiftCertificate> ascendingDate = service.getDescendingDate();
        assertEquals(expected,ascendingDate);
    }

    @Test
    public void ascendingNameSortPositive() {
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setName("Zurado")
                .build();
        List<GiftCertificate> current = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(testEntity);
            add(certificate);
        }};
        Mockito.doReturn(current).when(service).findAll();

        List<GiftCertificate> ascendingDate = service.getAscendingName();
        assertEquals(expected,ascendingDate);
    }
    @Test
    public void descendingNameSortPositive() {
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setName("Zurado")
                .build();
        List<GiftCertificate> current = new ArrayList<GiftCertificate>() {{
            add(testEntity);
            add(certificate);

        }};
        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        Mockito.doReturn(current).when(service).findAll();

        List<GiftCertificate> ascendingDate = service.getDescendingName();
        assertEquals(expected,ascendingDate);
    }

    @Test
    public void findCertificateTagsPositive() {
        Tag tagTest = Tag.Builder.newInstance()
                .setId(1)
                .setName("Test")
                .build();

        Set<Tag> expected = new HashSet<Tag>() {{
            add(tagTest);
        }};
        List<Tag> current = new ArrayList<Tag>() {{
            add(tagTest);
        }};
        when(tagService.findCertificateTags(testEntity.getId())).thenReturn(current);
        Set<Tag> certificateTags = service.findCertificateTags(testEntity.getId());
        assertEquals(expected,certificateTags);
    }

}
