package com.mockitopractice.services.impl;

import com.mockitopractice.services.TodoService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoBusinessImplMockTest {

    @Test
    public void testRetreiveTodosRelatedToSpring() {
        TodoService todoService = mock(TodoService.class);

        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Junit");

        when(todoService.retrieveTodos("Dummy")).thenReturn(strings);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        assertEquals(2, todoBusiness.retreiveTodosRelatedToSpring("Dummy").size());
    }

}