package com.epam.esm.dao;

import com.epam.esm.dao.impl.GiftCertificateDao;
import com.epam.esm.dao.reader.GiftCertificateMapper;
import com.epam.esm.domain.GiftCertificate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GiftCertificateDaoTest {
    private static GiftCertificateDao dao;

    public static DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:h2/init_schema.sql")
                .addScript("classpath:h2/test_data.sql")
                .build();
    }
    
    @BeforeAll
    public static void init() {
        dao = new GiftCertificateDao();
        dao.setJdbcTemplate(new JdbcTemplate(dataSource()));
        dao.setMapper(new GiftCertificateMapper());
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
        GiftCertificate certificate = GiftCertificate.Builder.newInstance()
                .setName("saveTestName")
                .setDescription("saveTestDescription")
                .setPrice(23.6f)
                .setDuration(10)
                .setCreateDate(LocalDateTime.parse("2020-07-07T15:15:15"))
                .setLastUpdateDate(LocalDateTime.parse("2020-07-07T15:15:15"))
                .build();
        dao.save(certificate);
        GiftCertificate beforeUpdate = dao.findByName(certificate.getName());
        beforeUpdate.setName("afterUpdate");
        dao.update(beforeUpdate);
        GiftCertificate afterUpdate = dao.findById(beforeUpdate.getId());
        dao.delete(afterUpdate.getId());
        assertNotEquals(certificate.getName(),afterUpdate.getName());
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
