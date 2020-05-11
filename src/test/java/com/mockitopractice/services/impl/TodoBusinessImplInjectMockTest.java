package com.mockitopractice.services.impl;

import com.mockitopractice.services.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplInjectMockTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoBusinessImpl todoBusiness;

    @Test
    public void testRetreiveTodosRelatedToSpring() {
        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Junit");
        when(todoService.retrieveTodos("Dummy")).thenReturn(strings);
        assertEquals(2, todoBusiness.retreiveTodosRelatedToSpring("Dummy").size());
    }
}
