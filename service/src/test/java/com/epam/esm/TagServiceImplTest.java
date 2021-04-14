package com.epam.esm;

import com.epam.esm.dao.TagsDao;
import com.epam.esm.dao.impl.TagsDaoImpl;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.TagService;

import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class TagServiceImplTest {

    @InjectMocks
   private TagServiceImpl tagServiceImpl;

    @Mock
   private TagsDao tagsDaoImpl;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private final  Tag tagTest = Tag.Builder.newInstance()
            .setId(1)
            .setName("Test")
            .build();


    @Test
    public void findByIdPositive() {
        when(tagsDaoImpl.findById(tagTest.getId())).thenReturn(tagTest);
        Tag tag = tagServiceImpl.findById(tagTest.getId());
        assertEquals(tag,tagTest);

    }
    @Test
    public void findByIdNegative() {
        when(tagsDaoImpl.findById(tagTest.getId())).thenReturn(null);
        assertThrows(EntityNotFoundException.class,() -> {
            tagServiceImpl.findById(tagTest.getId());
        });
    }

    @Test
    public void findByNamePositive() {
        when(tagsDaoImpl.findByName(tagTest.getName())).thenReturn(tagTest);
        Tag tag = tagServiceImpl.findByName(tagTest.getName());
        assertEquals(tag,tagTest);
    }
    @Test
    public void findByNameNegative() {
        when(tagsDaoImpl.findByName(tagTest.getName())).thenReturn(null);
        assertThrows(EntityNotFoundException.class,() -> {
            tagServiceImpl.findByName(tagTest.getName());
        });
    }


}
