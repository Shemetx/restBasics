package com.epam.esm;

import com.epam.esm.dao.TagDao;
import com.epam.esm.domain.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class TagServiceImplTest {

    private final Tag tagTest = new Tag(1, "Test");
    @InjectMocks
    private TagServiceImpl tagServiceImpl;
    @Mock
    private TagDao tagsDaoImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdPositive() {
        when(tagsDaoImpl.findById(tagTest.getId())).thenReturn(Optional.of(tagTest));
        Tag tag = tagServiceImpl.findById(tagTest.getId());
        assertEquals(tag, tagTest);

    }

    @Test
    public void findByIdNegative() {
        when(tagsDaoImpl.findById(tagTest.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                tagServiceImpl.findById(tagTest.getId()));
    }

    @Test
    public void findByNamePositive() {
        when(tagsDaoImpl.findByName(tagTest.getName())).thenReturn(Optional.of(tagTest));
        Tag tag = tagServiceImpl.findByName(tagTest.getName());
        assertEquals(tag, tagTest);
    }

    @Test
    public void findByNameNegative() {
        when(tagsDaoImpl.findByName(tagTest.getName())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                tagServiceImpl.findByName(tagTest.getName()));
    }


}
