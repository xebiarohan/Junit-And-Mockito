package com.mockitopractice.services.impl;

import com.mockitopractice.services.TodoService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TodoBusinessImplBDDTest {

    @Test
    public void testRetreiveTodosRelatedToSpring() {
        //Given
        TodoService todoService = mock(TodoService.class);

        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Junit");

        given(todoService.retrieveTodos("Dummy")).willReturn(strings);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        // when
        int todoCount = todoBusiness.retreiveTodosRelatedToSpring("Dummy").size();

        // then
        assertEquals(2, todoCount);

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring() {
        //Given
        TodoService todoService = mock(TodoService.class);

        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Junit");

        given(todoService.retrieveTodos("Dummy")).willReturn(strings);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        // when
        todoBusiness.deleteTodoNotRelatedToSprinng("Dummy");

        // Just wanter to test is it calling or not
        verify(todoService).deleteTodo("Learn Junit");

        // exactly wanted to test how many times it is calling
        verify(todoService, times(1)).deleteTodo("Learn Junit");

        // minimum 1 time it should get called
        verify(todoService, atLeastOnce()).deleteTodo("Learn Junit");

        // Atleast 5 times
        verify(todoService, atLeast(5)).deleteTodo(anyString());

        // Maximium how many times
        verify(todoService, atMost(6)).deleteTodo(anyString());

        //  should never be called
        verify(todoService,never()).deleteTodo("Learn Spring MVC");

    }
}
