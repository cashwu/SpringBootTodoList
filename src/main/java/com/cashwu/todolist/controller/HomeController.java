package com.cashwu.todolist.controller;

import com.cashwu.todolist.entity.Todo;
import com.cashwu.todolist.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cash
 */
@RestController
public class HomeController {

    private final TodoService todoService;

    public HomeController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index() {

//        todoService.saveTodo();

        Iterable<Todo> todo = todoService.getTodo();
        System.out.println(todo.toString());
        return "Hello World";
    }

    @GetMapping("/todo")
    public Iterable<Todo> getTodo() {
        return todoService.getTodo();
    }
}
