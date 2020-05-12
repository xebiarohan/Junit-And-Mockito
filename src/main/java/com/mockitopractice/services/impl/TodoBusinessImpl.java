package com.mockitopractice.services.impl;

import com.mockitopractice.services.TodoService;

import java.util.List;
import java.util.stream.Collectors;

public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retreiveTodosRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        return todos.stream().filter(x -> x.contains("Spring")).collect(Collectors.toList());
    }

    public void deleteTodoNotRelatedToSprinng(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        todos.stream().filter(x -> !x.contains("Spring")).forEach(x ->
                todoService.deleteTodo(x)
        );
    }
}
