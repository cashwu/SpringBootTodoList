package com.cashwu.todolist.service;

import com.cashwu.todolist.dao.TodoDao;
import com.cashwu.todolist.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TodoServiceTest {

    private TodoService todoService;
    private TodoDao mockTodoDao;

    @BeforeEach
    void setUp() {
        mockTodoDao = Mockito.mock(TodoDao.class);
        todoService = new TodoService(mockTodoDao);
    }

    @Test
    void getAll() {

        var expectTodo = new ArrayList<Todo>();
        Todo todo = new Todo();
        todo.setTask("aa");
        todo.setId(1);
        expectTodo.add(todo);

        when(mockTodoDao.findAll()).thenReturn(expectTodo);

        Iterable<Todo> todos = todoService.getTodo();

        Assertions.assertEquals(expectTodo, todos);
    }

    @Test
    void save() {

        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("task123");

        when(mockTodoDao.save(todo))
                .thenReturn(todo);

        Todo newTodo = todoService.saveTodo(todo);

        Assertions.assertEquals(1, newTodo.getId());
    }

    @Test
    void update() {

        Todo todo = new Todo();
        todo.setTask("task123");

        when(mockTodoDao.findById(1))
                .thenReturn(Optional.of(todo));

        todo.setTask("task456");

        Boolean isSuccess = todoService.updateTodo(1, todo);

        Assertions.assertEquals(true, isSuccess);
    }
    @Test
    void update_not_found() {

        when(mockTodoDao.findById(3))
                .thenReturn(Optional.empty());

        Todo todo = new Todo();
        todo.setTask("task123");

        Boolean isSuccess = todoService.updateTodo(3, todo);

        Assertions.assertEquals(false, isSuccess);
    }
}
