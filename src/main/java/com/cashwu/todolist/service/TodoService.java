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

        return todoDao.findById(id)
                .map(oldTodo -> {
                    oldTodo.setTask(todo.getTask());
                    oldTodo.setStatus(todo.getStatus());
                    todoDao.save(oldTodo);
                    return true;
                }).orElse(false);
    }

    public Boolean deleteTodo(Integer id) {

        return todoDao.findById(id)
                .map(oldTodo -> {
                    todoDao.deleteById(id);
                    return true;
                }).orElse(false);
    }
}
