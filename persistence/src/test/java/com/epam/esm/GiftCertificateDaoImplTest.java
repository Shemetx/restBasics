package com.epam.esm;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
public class GiftCertificateDaoImplTest {

    private GiftCertificateDao dao;

    @Autowired
    public void setDao(GiftCertificateDaoImpl dao) {
        this.dao = dao;
    }

    @Test
    public void findAllTest() {
        List<GiftCertificate> index = dao.findAll(1, 7);
        assertFalse(index.isEmpty());
    }

    @Test
    public void findByIdTest() {
        GiftCertificate byTagId = dao.findById(1);
        assertEquals("testNameFirst", byTagId.getName());
    }

    @Test
    public void findByTagIdTest() {
        Tag first = new Tag(2, "testTagSecond");
        Tag second = new Tag(3, "testTagThird");
        List<Tag> tags = new ArrayList<Tag>() {{
            add(first);
            add(second);
        }};
        List<GiftCertificate> byTagId = dao.findByTags(tags, 0, 7);
        assertEquals(2, byTagId.get(0).getId());
    }

    @Test
    public void findByPartNameTest() {
        List<GiftCertificate> first = dao.findByPartOfName("First", 0, 7);
        GiftCertificate certificate = first.get(0);
        assertEquals(certificate.getName(), "testNameFirst");
    }

    @Test
    public void findByPartDescriptionTest() {
        List<GiftCertificate> first = dao.findByPartOfDescription("First", 0, 7);
        GiftCertificate certificate = first.get(0);
        assertEquals(certificate.getDescription(), "testDescriptionFirst");
    }

    @Transactional
    @Test
    public void saveTest() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("saveTestName");
        certificate.setDescription("saveTestDescription");
        certificate.setPrice(23.6f);
        certificate.setDuration(10);
        certificate.setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
        certificate.setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
        GiftCertificate save = dao.save(certificate);
        dao.delete(save);
        assertEquals(certificate.getName(), save.getName());
    }

    @Transactional
    @Test
    public void updateTest() {
        GiftCertificate beforeUpdate = dao.findById(1);
        Float priceBefore = beforeUpdate.getPrice();
        GiftCertificate toUpdate = dao.findById(1);
        toUpdate.setPrice(16.76f);
        dao.update(beforeUpdate, toUpdate);
        assertNotEquals(priceBefore, toUpdate.getPrice());
    }

    @Transactional
    @Test
    public void deleteTest() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("saveTestName");
        certificate.setDescription("saveTestDescription");
        certificate.setPrice(23.6f);
        certificate.setDuration(10);
        certificate.setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
        certificate.setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"));

        GiftCertificate save = dao.save(certificate);
        List<GiftCertificate> beforeDelete = dao.findAll(0, 7);
        dao.delete(save);
        List<GiftCertificate> afterDelete = dao.findAll(0, 7);
        assertNotEquals(beforeDelete.size(), afterDelete.size());
    }
}
