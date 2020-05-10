package com.mockitopractice.services.impl;

import com.mockitopractice.services.TodoServiceStub;
import com.mockitopractice.services.TodoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoBusinessImplStubTest {

    @Test
    public void testRetreiveTodosRelatedToSpring() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);
        assertEquals(2, todoBusiness.retreiveTodosRelatedToSpring("Dummy").size());
        assertTrue(true);
    }
}