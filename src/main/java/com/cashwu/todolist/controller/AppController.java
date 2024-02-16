package com.cashwu.todolist.controller;

import com.cashwu.todolist.entity.Todo;
import com.cashwu.todolist.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

/**
 * @author cash
 */
@Controller
public class AppController {
    private final TodoService todoService;

    public AppController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("helloKey", "Hello World ~");
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("tt");
        todo.setStatus(123);
        model.addAttribute("todo", todo);

        model.addAttribute("gender", "female");

        var list = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        model.addAttribute("list", list);

        return "hello";
    }

    @GetMapping("/todo")
    public String getTodo(Model model) {
        model.addAttribute("todoList", todoService.getTodo());
        model.addAttribute("todo", new Todo());

        return "todo";
    }

    @PostMapping("/todo")
    public String saveTodo(@ModelAttribute Todo todo, Model model) {

        todoService.saveTodo(todo);
        model.addAttribute("todoList", todoService.getTodo());
        model.addAttribute("todo", new Todo());

        return "todo";
    }

}
