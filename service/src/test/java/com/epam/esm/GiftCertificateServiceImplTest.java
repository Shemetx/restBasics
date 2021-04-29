package com.epam.esm;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.util.PageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class GiftCertificateServiceImplTest {

    @InjectMocks
    @Spy
    private GiftCertificateServiceImpl service;

    @Mock
    private GiftCertificateDao dao;

    @Mock
    private TagService tagServiceImpl;

    @Mock
    private PageUtil pageUtil;

    final int page = 0;
    final int size = 7;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(pageUtil.getCorrectPage(page,size)).thenReturn(0);
    }

    private  final GiftCertificate testEntity = new GiftCertificate(1,"Test","Test description",3.6f,
            30,LocalDateTime.parse("2020-01-01T12:12:12"),LocalDateTime.parse("2020-03-03T14:14:14"));


    @Test
    public void findByPartOfNamePositive() {
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};

      when(dao.findByPartOfName(testEntity.getName(),page,size)).thenReturn(testList);
        List<GiftCertificate> list = service.findByPartOfName(testEntity.getName(),page,size);
        assertEquals(list,testList);
    }
    @Test
    public void findByPartOfNameNegative() {
        when(dao.findByPartOfName(testEntity.getName(),page,size)).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            service.findByPartOfName(testEntity.getName(),page,size);
        });
    }

    @Test
    public void findByPartOfDescriptionPositive() {
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        when(dao.findByPartOfDescription(testEntity.getDescription(),page,size)).thenReturn(testList);
        List<GiftCertificate> list = service.findByPartOfDescription(testEntity.getDescription(),page,size);
        assertEquals(list,testList);
    }
    @Test
    public void findByPartOfDescriptionNegative() {
        when(dao.findByPartOfDescription(testEntity.getDescription(),page,size)).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            service.findByPartOfName(testEntity.getDescription(),page,size);
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
    public void findAllByTagPositive() {
        Tag tagTest = new Tag(1,"Test");
        List<String> tagNames = new ArrayList<String>() {{
            add("Test");
        }};

        List<Tag> tagList = new ArrayList<Tag>() {{
            add(tagTest);
        }};
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        when(tagServiceImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findByTags(tagList,page,size)).thenReturn(testList);
        List<GiftCertificate> allByTag = service.findAllByTags(tagNames,page,size);
        assertEquals(allByTag,testList);
    }

    @Test
    public void findAllByTagNegative() {
        Tag tagTest = new Tag(1,"Test");
        List<String> tagNames = new ArrayList<String>() {{
            add("Test");
        }};

        List<Tag> tagList = new ArrayList<Tag>() {{
            add(tagTest);
        }};
        List<GiftCertificate> testList = new ArrayList<GiftCertificate>() {{
            add(testEntity);
        }};
        when(tagServiceImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        when(dao.findByTags(tagList,page,size)).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class,() -> {
            service.findAllByTags(tagNames,page,size); });

    }

    @Test
    public void ascendingDateSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"));

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};

        when(dao.findByOrderByCreateDateAsc(page,size)).thenReturn(expected);
        List<GiftCertificate> ascendingDate = service.getSortedList("asc","date",page,size);
        assertEquals(expected,ascendingDate);
    }

    @Test
    public void descendingDateSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setCreateDate(LocalDateTime.parse("2020-02-03T10:10:10"));

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        when(dao.findByOrderByCreateDateDesc(page,size)).thenReturn(expected);

        List<GiftCertificate> ascendingDate = service.getSortedList("desc","date",page,size);
        assertEquals(expected,ascendingDate);
    }

    @Test
    public void ascendingNameSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
                certificate.setName("Zurado");

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(testEntity);
            add(certificate);
        }};
        when(dao.findByOrderByNameAsc(page,size)).thenReturn(expected);

        List<GiftCertificate> ascendingDate = service.getSortedList("asc","name",page,size);
        assertEquals(expected,ascendingDate);
    }
    @Test
    public void descendingNameSortPositive() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Zurado");

        List<GiftCertificate> expected = new ArrayList<GiftCertificate>() {{
            add(certificate);
            add(testEntity);
        }};
        when(dao.findByOrderByNameDesc(page,size)).thenReturn(expected);

        List<GiftCertificate> ascendingDate = service.getSortedList("desc","name",page,size);
        assertEquals(expected,ascendingDate);
    }
}
