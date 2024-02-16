package com.cashwu.todolist.service;

import com.cashwu.todolist.dao.TodoDao;
import com.cashwu.todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author cash
 */
@Service
public class TodoService {

    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public Iterable<Todo> getTodo() {
        return todoDao.findAll();
    }

    public void saveTodo(Todo todo) {

        todo.setCreateTime(LocalDateTime.now());
        todoDao.save(todo);
    }
}
