package com.epam.esm;

import com.epam.esm.dao.CertificatesTagsDao;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.service.CertificateTagsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class CertificateTagsServiceTest {

    @InjectMocks
    private CertificateTagsService service;

    @Mock
    private CertificatesTagsDao dao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private final CertificatesTags test = CertificatesTags.Builder.newInstance()
            .setCertificateId(1)
            .setTagId(1)
            .build();

    @Test
    public void findByBothIdPositive() {
        when(dao.findByBothId(test.getCertificateId(), test.getTagId())).thenReturn(test);
        CertificatesTags byIds = service.findByIds(test.getCertificateId(), test.getTagId());
        assertEquals(test,byIds);
    }

}
