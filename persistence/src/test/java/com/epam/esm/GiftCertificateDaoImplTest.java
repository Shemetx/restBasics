//package com.epam.esm;
//
//import com.epam.esm.dao.GiftCertificateDao;
//import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
//import com.epam.esm.domain.GiftCertificate;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest( webEnvironment =
//        SpringBootTest.WebEnvironment.MOCK)
//@ActiveProfiles("dev")
//public class GiftCertificateDaoImplTest {
//
//    private GiftCertificateDao dao;
//
//    @Autowired
//    public void setDao(GiftCertificateDaoImpl dao) {
//        this.dao = dao;
//    }
//
//    @Test
//     public void findAllTest() {
//        List<GiftCertificate> index = dao.findAll();
//        assertFalse(index.isEmpty());
//    }
//
//    @Test
//    public void findByIdTest() {
//        GiftCertificate byTagId = dao.findById(1);
//        assertEquals("testNameFirst",byTagId.getName());
//    }
//
//
//
//    @Test
//    public void findByTagIdTest() {
//        List<GiftCertificate> byTagId = dao.findByTag(2);
//        assertEquals(2,byTagId.size());
//    }
//
//    @Test
//    public void findByPartNameTest() {
//        List<GiftCertificate> first = dao.findByPartOfName("First");
//        GiftCertificate certificate = first.get(0);
//        assertEquals(certificate.getName(),"testNameFirst");
//    }
//
//    @Test
//    public void findByPartDescriptionTest() {
//        List<GiftCertificate> first = dao.findByPartOfDescription("First");
//        GiftCertificate certificate = first.get(0);
//        assertEquals(certificate.getDescription(),"testDescriptionFirst");
//    }
//
//    @Test
//    public void saveTest() {
//        GiftCertificate certificate = new GiftCertificate();
//        certificate.setName("saveTestName");
//        certificate.setDescription("saveTestDescription");
//        certificate.setPrice(23.6f);
//        certificate.setDuration(10);
//        certificate.setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
//        certificate.setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
//        GiftCertificate save = dao.save(certificate);
//        dao.delete(save);
//        assertEquals(certificate.getName(),save.getName());
//    }
//
//    @Test
//    public void updateTest() {
//        GiftCertificate beforeUpdate = dao.findById(1);
//        GiftCertificate certificate = new GiftCertificate();
//        certificate.setId(1);
//        certificate.setPrice(16.78f);
//        GiftCertificate afterUpdate1 = dao.findById(1);
//        dao.update(afterUpdate1,certificate);
//        GiftCertificate afterUpdate = dao.findById(1);
//        assertNotEquals(beforeUpdate.getPrice(),afterUpdate.getPrice());
//    }
//
//    @Test
//    public void deleteTest() {
//        GiftCertificate certificate = new GiftCertificate();
//        certificate.setName("saveTestName");
//        certificate.setDescription("saveTestDescription");
//        certificate.setPrice(23.6f);
//        certificate.setDuration(10);
//        certificate.setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
//        certificate.setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"));
//
//        GiftCertificate save = dao.save(certificate);
//        List<GiftCertificate> beforeDelete = dao.findAll();
//        dao.delete(save);
//        List<GiftCertificate> afterDelete = dao.findAll();
//        assertNotEquals(beforeDelete.size(),afterDelete.size());
//    }
//}
