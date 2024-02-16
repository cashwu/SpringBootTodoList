package com.cashwu.todolist.service;

import com.cashwu.todolist.dao.TodoDao;
import com.cashwu.todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Optional<Todo> getTodo(Integer id) {

        return todoDao.findById(id);
    }

    public Todo saveTodo(Todo todo) {

        todo.setCreateTime(LocalDateTime.now());
        return todoDao.save(todo);
    }

    public Boolean updateTodo(Integer id, Todo todo) {

        var optionalTodo = todoDao.findById(id);

        if (optionalTodo.isEmpty()) {
            return false;
        }

        var oldTodo = optionalTodo.get();
        oldTodo.setTask(todo.getTask());
        oldTodo.setStatus(todo.getStatus());

        todoDao.save(oldTodo);
        return true;
    }

    public Boolean deleteTodo(Integer id) {
        Optional<Todo> oldTodo = todoDao.findById(id);

        if (oldTodo.isEmpty()) {
            return false;
        }
        todoDao.deleteById(id);
        return true;
    }
}
