package com.mockitopractice.services;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC","Learn Spring","Learn Junit");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
