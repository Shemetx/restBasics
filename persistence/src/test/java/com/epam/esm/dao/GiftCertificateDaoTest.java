package com.epam.esm.dao;

import com.epam.esm.config.PersistenceDevConfig;
import com.epam.esm.domain.GiftCertificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = PersistenceDevConfig.class)
public class GiftCertificateDaoTest {

    private  GiftCertificateDao dao;

    @Autowired
    public void setDao(GiftCertificateDao dao) {
        this.dao = dao;
    }

    @Test
     public void findAllTest() {
        List<GiftCertificate> index = dao.index();
        assertFalse(index.isEmpty());
    }

    @Test
    public void findByIdTest() {
        GiftCertificate byTagId = dao.findById(1);
        assertEquals("testNameFirst",byTagId.getName());
    }

    @Test
    public void findByNameTest() {
        GiftCertificate testNameFirst = dao.findByName("testNameFirst");
        assertEquals("testNameFirst",testNameFirst.getName());

    }

    @Test
    public void findByTagIdTest() {
        List<GiftCertificate> byTagId = dao.findByTagId(2);
        assertEquals(2,byTagId.size());
    }

    @Test
    public void findByPartNameTest() {
        List<GiftCertificate> first = dao.findByPartOfName("First");
        GiftCertificate certificate = first.get(0);
        assertEquals(certificate.getName(),"testNameFirst");
    }

    @Test
    public void findByPartDescriptionTest() {
        List<GiftCertificate> first = dao.findByPartOfDescription("First");
        GiftCertificate certificate = first.get(0);
        assertEquals(certificate.getDescription(),"testDescriptionFirst");
    }

    @Test
    public void saveTest() {
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setName("saveTestName")
                .setDescription("saveTestDescription")
                .setPrice(23.6f)
                .setDuration(10)
                .setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"))
                .setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"))
                .build();
        dao.save(certificate);
        GiftCertificate byName = dao.findByName(certificate.getName());
        dao.delete(byName.getId());
        assertEquals(certificate.getName(),byName.getName());
    }

    @Test
    public void updateTest() {
        GiftCertificate beforeUpdate = dao.findById(1);
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setId(1)
                .setPrice(16.78f)
                .build();
        dao.update(certificate);
        GiftCertificate afterUpdate = dao.findById(1);
        assertNotEquals(beforeUpdate.getPrice(),afterUpdate.getPrice());
    }

    @Test
    public void deleteTest() {
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setName("saveTestName")
                .setDescription("saveTestDescription")
                .setPrice(23.6f)
                .setDuration(10)
                .setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"))
                .setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"))
                .build();
        dao.save(certificate);
        List<GiftCertificate> beforeDelete = dao.index();
        GiftCertificate certificateId = dao.findByName(certificate.getName());
        dao.delete(certificateId.getId());
        List<GiftCertificate> afterDelete = dao.index();
        assertNotEquals(beforeDelete.size(),afterDelete.size());
    }
}
