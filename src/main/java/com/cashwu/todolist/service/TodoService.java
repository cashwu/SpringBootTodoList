package com.cashwu.todolist.service;

import com.cashwu.todolist.dao.PersonDao;
import com.cashwu.todolist.dao.TodoDao;
import com.cashwu.todolist.entity.Person;
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
    private final PersonDao personDao;

    public TodoService(TodoDao todoDao, PersonDao personDao) {
        this.todoDao = todoDao;
        this.personDao = personDao;
    }

    public Iterable<Todo> getTodo() {
        return todoDao.findAll();
    }

    public Optional<Todo> getTodo(Integer id) {

        return todoDao.findById(id);
    }

    public Todo saveTodo(Todo todo) {

        Optional<Person> personOptional = personDao.findById(1);

        return personOptional.map(person -> {
                    todo.setPerson(person);
                    todo.setCreateTime(LocalDateTime.now());
                    return todoDao.save(todo);
                })
                .orElse(null);
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
