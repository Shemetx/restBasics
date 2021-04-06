package com.epam.esm;

import com.epam.esm.dao.impl.TagsDao;
import com.epam.esm.domain.Tag;
import com.epam.esm.service.TagService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class TagServiceTest {

    @InjectMocks
    TagService tagService;

    @Mock
    TagsDao tagsDao;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private final  Tag tagTest = Tag.Builder.newInstance()
            .setId(1)
            .setName("Test")
            .build();


    @Test
    public void findByIdPositiveTest() {
        when(tagsDao.findById(tagTest.getId())).thenReturn(tagTest);
        Tag tag = tagService.findById(tagTest.getId());
        assertEquals(tag,tagTest);

    }
}
