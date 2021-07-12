package com.epam.esm;


import com.epam.esm.conf.DaoTestConfig;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DaoTestConfig.class)
@ActiveProfiles("dev")
public class GiftCertificateDaoTest {

    private GiftCertificateDao dao;
    private int page = 0;
    private int size = 7;
    private final String CREATE_TIME = "2020-07-07T15:15:15";

    @Autowired
    public void setDao(GiftCertificateDao dao) {
        this.dao = dao;
    }

    @Test
    public void findAllTest() {
        Page<GiftCertificate> index = dao.findAll(PageRequest.of(page,size));
        assertFalse(index.isEmpty());
    }

    @Test
    public void findByTagIdTest() {
        Tag first = new Tag(2, "testTagSecond");
        Tag second = new Tag(3, "testTagThird");
        List<Tag> tags = new ArrayList<>();
        tags.add(first);
        tags.add(second);
        Page<GiftCertificate> byTagId = dao.findBySeveralTags(tags, PageRequest.of(page,size));
        assertEquals(2, byTagId.getContent().get(0).getId());
    }


    @Transactional
    @Test
    public void saveTest() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("saveTestName");
        certificate.setDescription("saveTestDescription");
        certificate.setPrice(23.6f);
        certificate.setDuration(10);
        certificate.setCreateDate(LocalDateTime.parse(CREATE_TIME));
        certificate.setLastUpdateDate(LocalDateTime.parse(CREATE_TIME));
        GiftCertificate save = dao.save(certificate);
        dao.delete(save);
        assertEquals(certificate.getName(), save.getName());
    }

    @Transactional
    @Test
    public void updateTest() {
        Optional<GiftCertificate> beforeUpdate = dao.findById(1);
        Float priceBefore = beforeUpdate.get().getPrice();
        Optional<GiftCertificate> toUpdate = dao.findById(1);
        toUpdate.get().setPrice(16.76f);
        assertNotEquals(priceBefore, toUpdate.get().getPrice());
    }

    @Transactional
    @Test
    public void deleteTest() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("saveTestName");
        certificate.setDescription("saveTestDescription");
        certificate.setPrice(23.6f);
        certificate.setDuration(10);
        certificate.setCreateDate(LocalDateTime.parse(CREATE_TIME));
        certificate.setLastUpdateDate(LocalDateTime.parse(CREATE_TIME));

        GiftCertificate save = dao.save(certificate);
        Page<GiftCertificate> beforeDelete = dao.findAll(PageRequest.of(page,size));
        dao.delete(save);
        Page<GiftCertificate> afterDelete = dao.findAll(PageRequest.of(page,size));
        assertNotEquals(beforeDelete.getTotalElements(), afterDelete.getTotalElements());
    }
}
